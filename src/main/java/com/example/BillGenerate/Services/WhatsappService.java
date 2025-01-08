package com.example.BillGenerate.Services;

import com.example.BillGenerate.Configuration.TwilioConfiguration;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {

    @Autowired
    TwilioConfiguration configuration;

    public void whatsappMsg(String customerNumber, String message) {
        String customerPhoneNumber = "+" + customerNumber;

        Message.creator(
                new PhoneNumber("whatsapp:+919427110401"),
                new PhoneNumber("whatsapp:+14155238886."),
                message
        ).create();

        System.out.println("sms sent to customer" + customerPhoneNumber + "with message" + message);
    }
}
