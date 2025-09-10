package com.xworkz.project.runner;

import com.xworkz.project.service.MailService;
import com.xworkz.project.configure.ProjectConfigure;
import com.xworkz.project.util.GenerateOTPUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestRunner {
    public static void main(String[] args) {
//        // Load Spring context from your configuration class
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(ProjectConfigure.class);
//
//        // Get the MailService bean (managed by Spring)
//        MailService mailService = context.getBean(MailService.class);
//
//        // Now the autowired javaMailSender will NOT be null
//        mailService.sendRegisterEmail("sahanap1708@gmail.com", "Sahana P");
//
//        context.close();
        int otp=GenerateOTPUtil.generateOTP();
        System.out.println(otp);
    }
}
