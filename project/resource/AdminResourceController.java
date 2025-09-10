package com.xworkz.project.resource;

import com.xworkz.project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdminResourceController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/checkEmail", produces = "text/plain")
    public String getByEmail(@RequestParam("email") String email) {
        System.out.println("running in getByEmail");
        boolean exists = adminService.getByEmail(email);
        return String.valueOf(exists); // returns "true" or "false" as plain text
    }
    @PostMapping("/sendotp")
    public boolean sendOtp(@RequestParam String email, Model model) {
        boolean sent = adminService.generateAndSaveOtp(email);

        if (sent) {
                return true;
        }
        return false;
    }
}

