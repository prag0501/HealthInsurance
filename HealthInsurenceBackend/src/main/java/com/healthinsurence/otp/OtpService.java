package com.healthinsurence.otp;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

   
    public String generateOtp() {
        
        Date d = new Date();
         
        SimpleDateFormat sd = new SimpleDateFormat("mmss");
      
        return sd.format(d) ;
    }

 }
