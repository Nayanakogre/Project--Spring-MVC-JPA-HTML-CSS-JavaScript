package com.xworkz.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class MailService {
    @Autowired
    private  JavaMailSender javaMailSender;
    public  void sendRegisterEmail(String email,String userName)
    {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("kjnayana8@gmail.com");
        simpleMailMessage.setSubject("project-mail-check");
        simpleMailMessage.setText("Thank you for registering with this application");
        javaMailSender.send(simpleMailMessage);
    }
    public void sendRegisterEmail(String email, String userName, int otp) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("kjnayana8@gmail.com");
        simpleMailMessage.setSubject("OTP for Admin Login");
        simpleMailMessage.setText("Hello " + userName + ",\n\nYour OTP for admin login is: " + otp + "\n\nRegards,\nProject Team");
        javaMailSender.send(simpleMailMessage);
    }
    public void sendRegisterEmailOtp(String email, String userName, int otp) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("kjnayana8@gmail.com");
        simpleMailMessage.setSubject("OTP for Reset Password");
        simpleMailMessage.setText("Hello " + userName + ",\n\nYour OTP for reset password is: " + otp + "\n\nRegards,\nProject Team");
        javaMailSender.send(simpleMailMessage);
    }


}
