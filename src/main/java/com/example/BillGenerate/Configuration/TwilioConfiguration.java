package com.example.BillGenerate.Configuration;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfiguration {

    private String accountSid;

    private String authToken;

    private String trialNumber;


    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }
}

