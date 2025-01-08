package com.example.BillGenerate.Schedular;

import com.example.BillGenerate.Services.EmailService;
import com.example.BillGenerate.Services.ReportService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ReportSchedulor {

    @Autowired
    private ReportService reportService;

    @Autowired
    private EmailService emailService;


    @PostConstruct
    @Scheduled(cron ="0 0 22 * * *")
    public void sendReport() throws IOException {
        try {
            reportService.generateReport();
            emailService.sendReportEmail();
        }catch(IOException|jakarta.mail.MessagingException e){
            e.printStackTrace();
        }
    }

}

