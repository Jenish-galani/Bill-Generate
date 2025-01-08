package com.example.BillGenerate.Configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix="admin")
public class AdminConfiguration {

    private String adminName;
    private String adminContactNumber;
    private String Adminemail;

    private String adminWhatsappNumber;



}

