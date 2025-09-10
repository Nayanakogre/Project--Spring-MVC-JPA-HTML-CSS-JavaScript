package com.xworkz.project.controller;

import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/sendotp")
    public String sendOtp(@RequestParam String email, Model model) {
        boolean sent = adminService.generateAndSaveOtp(email);
        System.out.println("OTP sent status for " + email + " = " + sent);
        System.out.println(email);

        if (sent) {
            model.addAttribute("otpSent", "OTP has been sent to your email: " + email);
        } else {
            model.addAttribute("otpError", "Failed to send OTP to " + email + ". Please try again.");
        }
        return "adminLogin"; // JSP page
    }


    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam String email, @RequestParam int otp, Model model) {
        System.out.println("Verifying OTP: " + otp + " for Email: " + email);

        boolean isValid = adminService.verifyOtp(email, otp);
        if (isValid) {
            model.addAttribute("message", "OTP verified successfully!");
            return "AdminPage";
        } else {
            model.addAttribute("error", "Invalid OTP");
            return "adminLogin";
        }
    }
    @GetMapping("/viewUsers")
        public String viewUsers(Model model)
    {
       List<SignUpDto> dto= adminService.viewUsers();
        System.out.println("dto"+dto);
       if(dto!=null)
       {
           model.addAttribute("dto",dto);
       }
       else{
           model.addAttribute("error","user fetching unsuccessful");
       }
       return "AdminPage";
    }
}
