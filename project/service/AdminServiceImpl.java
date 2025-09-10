package com.xworkz.project.service;

import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.entity.AdminEntity;
import com.xworkz.project.entity.SignUpEntity;
import com.xworkz.project.repository.AdminRepo;
import com.xworkz.project.util.GenerateOTPUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class AdminServiceImpl implements AdminService {
    private AdminRepo adminRepo;
    private MailService mailService;

    @Override
    public boolean generateAndSaveOtp(String email) {
        AdminEntity admin = adminRepo.findByEmail(email);
        System.out.println(admin);
        if (admin != null ) {
            int otp = GenerateOTPUtil.generateOTP();
            System.out.println(otp);

            // Send OTP by email
            mailService.sendRegisterEmail(email, admin.getUserName(), otp);

            // Update OTP in database
            return adminRepo.updateOtpByEmail(email, otp);
        }
        return false;
    }

    public boolean verifyOtp(String email, int otp) {

        if (email != null) {
            AdminEntity entity = adminRepo.findByEmail(email);
            if (entity != null) {
                if (entity.getOtp() != null && entity.getOtp().equals(otp)) {
                    return true;
                }
            }
            System.out.println("email:" + email);

        }
        return false;
    }

    @Override
    public boolean getByEmail(String email) {
        AdminEntity entity = adminRepo.getByEmail(email);
        System.out.println(entity);
        if (entity != null) {
            System.out.println(entity);
            return true;
        } else {
            System.out.println("No admin found for email: " + email);
            return false;
        }
    }

    @Override
    public List<SignUpDto> viewUsers() {

        List<SignUpEntity> users = adminRepo.viewUser();

        return users.stream().map(this::convert).collect(Collectors.toList());
    }

    public SignUpDto convert(SignUpEntity entity) {
        SignUpDto signUpDto = new SignUpDto();
        BeanUtils.copyProperties(entity, signUpDto);
        System.out.println("service"+entity);
        System.out.println("Signup Dto: " + signUpDto);
        return signUpDto;
    }

}



