package com.xworkz.project.controller;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.entity.SignUpEntity;
import com.xworkz.project.service.SignUpService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/")
public class SignUpController {
    @Autowired
    private SignUpService service;
//    private SignUpDto signUpDto;

    @PostMapping("/signup")
    public String save(SignUpDto signUpDto, Model model) {

        System.out.println(signUpDto);
        boolean save = service.save(signUpDto);
        if (save) {
            model.addAttribute("add", "saved successfully");
            return "signIn";
        } else {
            model.addAttribute("add", "not saved");
        }
        return "signUp";
    }

    @GetMapping("/getByEmail")
    public String getByEmail(@RequestParam("email") String email, Model model) {
        SignUpDto dto = service.findDtoByEmail(email);
        System.out.println(dto);

        if (dto != null) {

            model.addAttribute("dto", dto);

        }
        return "update";
    }
//    @GetMapping("/getByEmail")
//    public String chcekEmail(@RequestParam("email") String email, Model model) {
//        boolean isEmail = service.findByEmail(email);
//        if(isEmail)
//        {
//            model.addAttribute("email","fetched successfully");
//        }
//        else{
//            model.addAttribute("email","fetched successfully");
//        }
//        return "signIn";
//    }


    @PostMapping("/update")
    public String updateUser(SignUpDto dto, Model model) {
        boolean updated = service.updateUser(dto);
        System.out.println("controller" + dto);

        if (updated) {
            model.addAttribute("message", "Profile updated successfully");
        } else {
            model.addAttribute("message", "Failed to update profile");
        }

        return "profile";

    }

    @PostMapping("deleteByEmail")
    public String deleteByName(String email, Model model) {
        System.out.println(email);
        boolean deleted = service.deleteByEmail(email);
        System.out.println(deleted);
        if (deleted) {
            model.addAttribute("deleted", "Deleted successfully!");
            return "profile";
        } else {
            model.addAttribute("deleted", "Deleted failed");
            return "profile";
        }
    }

    @PostMapping("/softDelete")
    public String softDelete(@RequestParam("email") String email, Model model) {
        boolean result = service.softDeleteByEmail(email);
        System.out.println(email);
        System.out.println("deleted successfully");
        if (result) {

            model.addAttribute("delete", "deleted successfully");

        } else {
            model.addAttribute("delete", "deleted successfully");
        }
        return "profile";
    }

    @PostMapping("/sendOtpPassword")
    public String sendOtpPassword(@RequestParam("email") String email, Model model) {
        boolean sent = service.generateAndSendOtp(email);

        if (sent) {
            model.addAttribute("email", email);
            model.addAttribute("showOtpForm", true);
            return "forgotPassword";
        } else {
            model.addAttribute("message", "Email not registered or failed to send OTP");
            return "forgotPassword";
        }
    }

    @PostMapping("/verifyUserOtp")
    public String verifyOtp(@RequestParam String email,
                            @RequestParam String otp,
                            Model model) {

        boolean isValid = service.isOtpValid(email, otp);

        if (isValid) {
            model.addAttribute("email", email);
            return "resetPassword";
        } else {
            model.addAttribute("message", "Invalid OTP. Please try again.");
            model.addAttribute("showOtpForm", true);
            model.addAttribute("email", email);
            return "forgotPassword";
        }
    }

    byte[] bytes;

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("email") String email,
                              @RequestParam("image") MultipartFile file,
                              Model model) {
        try {
            if (email == null || file == null || file.isEmpty()) {
                System.out.println(email);
                System.out.println(file);
                model.addAttribute("message", " Email or image is missing.");
                return "imageUpload";
            }

            byte[] bytes = file.getBytes();

            Path path = Paths.get("C:\\Users\\DELL\\Desktop\\Java Ful stack Project\\Images\\"
                    + email + "_" + System.currentTimeMillis());
            Files.write(path, bytes);

            String imageName = file.getOriginalFilename();
            String profileName = path.getFileName().toString();

            service.updateImageByEmail(email, bytes, imageName, profileName);

            model.addAttribute("message", "✅ Image updated successfully!");
            return "imageUpload";

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "❌ Image update failed.");
            return "imageUpload";
        }
    }
    @GetMapping("/downloadImagee")
    public void downloadImage(@RequestParam("email") String email, HttpServletResponse response) {
        SignUpEntity entity = service.downloadImage(email);

        if (entity != null && entity.getProfileImage() != null) {
            try {
                response.setContentType("image/jpeg/png/jpg");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + entity.getImageName() + "\"");

                OutputStream out = response.getOutputStream();
                out.write(entity.getProfileImage());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found for email: " + email);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}






