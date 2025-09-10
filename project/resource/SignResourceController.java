package com.xworkz.project.resource;

import com.xworkz.project.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SignResourceController {

    @Autowired
    private SignUpService service;

    @GetMapping("/emailCheck")
    public ResponseEntity<String> checkEmail(@RequestParam("email") String email) {
        System.out.println("Running in check email");
        boolean exists = service.findByEmail(email);
        return ResponseEntity.ok(String.valueOf(exists)); // return as plain text
    }


    @GetMapping(value = "/contactCheck", produces = "text/plain")
    public boolean checkContact(@RequestParam("contactNumber") long contactNo) {
        System.out.println("Running in check contact");
        return service.findByContact(contactNo);
    }

//    @GetMapping("/resetPassword")
//    public String showResetPassword(@RequestParam("email") String email, Model model) {
//        model.addAttribute("email", email);
//        return "resetPassword";
//    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(
            @RequestParam String email,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword) {

        if (!newPassword.equals(confirmPassword)) {
            return ResponseEntity.ok("false");
        }

        boolean updated = service.updatePassword(email.trim().toLowerCase(), newPassword);

        if (updated) {
            return ResponseEntity.ok("true");
        } else {
            return ResponseEntity.ok("false");
        }
    }


}


