package com.example.BillGenerate.Services;

import com.example.BillGenerate.Configuration.AdminConfiguration;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AdminConfiguration adminConfiguration;

    public void sendReportEmail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,true);

        String date=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String filePath = "D:\\Bill Generation-d\\Bill-Generation\\src\\main\\resources\\reports/products_inventory_report_" + date + ".csv";

        helper.setFrom(adminConfiguration.getAdminemail());
        helper.setTo(adminConfiguration.getAdminemail());
        helper.setSubject("Daily Product and Inventory Report-"+date);
        helper.setText("please find the attached dailY Rrports of produt and inventory for"+date+".");

        File file= new File(filePath);
        helper.addAttachment("products_inventory_report"+date+".csv",file);

        mailSender.send(mimeMessage);

    }
    public void senEmail(String emailTO,String subject,String message,String emailForm)throws MessagingException{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,true);

        helper.setTo(emailTO);
        helper.setSubject(subject);
        helper.setText(message);
        helper.setFrom(emailForm);

        mailSender.send(mimeMessage);
    }


}
