package com.example.BillGenerate.Services;

import com.example.BillGenerate.Configuration.AdminConfiguration;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    @Autowired
    SmsService smsService;

    @Autowired
    EmailService emailService;


    @Autowired
    AdminConfiguration adminConfiguration;

    @Autowired
    WhatsappService whatsappService;

    public void sendAlert(long productId,String productName,int inventory){
        String message=String.format(
                "⚠️ Alert: The inventory for product ID %d (Product Name: %s) is below the threshold. 🚨 Remaining Stock: %d. Please restock soon!",
                productId,
                productName,
                inventory
        );

        //send alert message
        smsService.sendSms(adminConfiguration.getAdminContactNumber(),message);
        whatsappService.whatsappMsg(adminConfiguration.getAdminContactNumber(),message);

        //send e-mail

        try{
            String emailSubject = "🚨 Inventory Alert: Low Stock for ***" + productName + "***";
            String emailTO = adminConfiguration.getAdminemail();
            String emailForm =adminConfiguration.getAdminemail();
            emailService.senEmail(emailTO,emailSubject,message,emailForm);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
