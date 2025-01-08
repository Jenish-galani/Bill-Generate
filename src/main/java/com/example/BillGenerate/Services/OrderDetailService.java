package com.example.BillGenerate.Services;

import com.example.BillGenerate.Model.*;
import com.example.BillGenerate.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderDetailService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BillGenerationRepository billGenerationRepository;

    @Autowired
    SmsService smsService;

    @Autowired
    WhatsappService whatsAppMessage;

    @Autowired
    BillService billService;

    @Autowired
    AlertService alertService;

    private static final int  INVENTORY_THRESHOLD = 10;

    public ResponseDTO<OrderDetail> placeOrder(OrderDetail orderDetail){
        try {
            for (OrderItem orderItem : orderDetail.getOrderItemList()) {
                Product product = productRepository.findById(orderItem.getProductId()).orElseThrow(() -> new RuntimeException("Product not found with ID: " + orderItem.getProductId()));
                if (product.getInventory() < orderItem.getQuantity()) {
                    return new ResponseDTO<>(null, HttpStatus.BAD_REQUEST, "Insufficient inventory for productid:" + orderItem.getProductId());
                }
            }

            Customer customer =customerRepository.findById(orderDetail.getCustomerId()).orElseThrow(()->new RuntimeException("product not found with id"+orderDetail.getCustomerId()));
            String customerPhoneNumber=String.valueOf(customer.getMobileNumber());

            boolean paymentSuccess = processPayment();
            if(!paymentSuccess){
                String paymentFailed=String.format(
                        "⚠️ Hello %s! Unfortunately, your order could not be processed due to a payment failure. Please try again or contact support for assistance. We're here to help! 🙏",
                        customer.getName()
                );
                smsService.sendSms(customerPhoneNumber, paymentFailed);
                whatsAppMessage.whatsappMsg(customerPhoneNumber, paymentFailed);
                return new ResponseDTO<>(null, HttpStatus.PAYMENT_REQUIRED, "payment failed");
            }

            //save all details
            OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
            orderItemRepository.saveAll(orderDetail.getOrderItemList());
            Bill bills = billService.generateBill(savedOrderDetail);
            billGenerationRepository.save(bills);

            // payment success msg
            String paymentSuccessMsg = String.format(
                    "🎉 Hello %s! 🛒 Your order (ID: %s) has been successfully placed! 💳 Total payment: ₹%.2f. Thank you for choosing us! 🙏 We'll notify you once it's on its way. 🚚✨",
                    customer.getName(),
                    orderDetail.getOrderId(),
                    bills.getTotalAmount()
            );
            smsService.sendSms(customerPhoneNumber, paymentSuccessMsg);
            whatsAppMessage.whatsappMsg(customerPhoneNumber, paymentSuccessMsg);

            for(OrderItem orderItem:savedOrderDetail.getOrderItemList()){
                Product product = productRepository.findById(orderItem.getProductId()).orElseThrow(()->new RuntimeException("product not found with ID "+orderItem.getProductId()));
                product.setInventory(product.getInventory() - orderItem.getQuantity());

                productRepository.save(product);

                //alert to admin for threshold product
                if (product.getInventory() < INVENTORY_THRESHOLD) {
                    alertService.sendAlert(product.getProductId(), product.getProductName(), product.getInventory());
                }
            }

            return new ResponseDTO<>(savedOrderDetail, HttpStatus.OK, "Order placed successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO<>(null, HttpStatus.INTERNAL_SERVER_ERROR, "failed to place");
        }
    }

    private boolean processPayment() {
        Random random = new Random();
        return random.nextInt(4) != 0;
    }

}
