package com.example.BillGenerate.Controller;

import com.example.BillGenerate.Model.OrderDetail;
import com.example.BillGenerate.Model.ResponseDTO;
import com.example.BillGenerate.Repositories.OrderDetailRepository;
import com.example.BillGenerate.Services.EmailServ;
import com.example.BillGenerate.Services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private EmailServ emailService;

    @PostMapping("placeOrder")
    public ResponseDTO<OrderDetail> placeorder(@RequestBody OrderDetail orderDetail){
        return orderDetailService.placeOrder(orderDetail);
    }

    private boolean emailSent = false; // Track if the email has been sent

    public void checkAndSendEmail() {
        if (!emailSent) {
            String to = "jenishpvtltd1@example.com";
            String subject = "111Condition-Based Email";
            String text = "111This email is sent based on a specific condition.";
            emailService.sendEmail(to, subject, text);
            emailSent = true; // Update the flag to ensure the email is sent only once
            System.out.println("Condition-based email sent!");
        } else {
            System.out.println("Email already sent; skipping.");
        }
    }
}
