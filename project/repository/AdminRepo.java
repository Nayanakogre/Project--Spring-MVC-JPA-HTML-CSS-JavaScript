package com.xworkz.project.repository;

import com.xworkz.project.entity.AdminEntity;
import com.xworkz.project.entity.SignUpEntity;

import java.util.List;

public interface AdminRepo {
    public AdminEntity findByEmail(String email);
    public boolean updateOtpByEmail(String email, int otp);
    boolean verifyOtp(String email);
    public AdminEntity getByEmail(String email);
    List<SignUpEntity> viewUser();
}

