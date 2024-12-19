package com.healthinsurence.otp;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    
    public String generateOTP() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); 
        return String.valueOf(otp);
    }

      public void sendOtpEmail(String toEmail, String otp) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlMessage = "<h3>Your OTP Code</h3>"
                + "<p>Your OTP code is: <strong>" + otp + "</strong></p>"
                + "<p>This code is valid for 10 minutes.</p>";

        helper.setFrom("your-email@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject("Your OTP Code");
        helper.setText(htmlMessage, true); 

        mailSender.send(mimeMessage);
       
    }
}
