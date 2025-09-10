package com.xworkz.project.repository;

import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.entity.SignUpEntity;

import java.io.InputStream;

public interface SignUpRepo {
    boolean save(SignUpEntity signUpEntity);
    SignUpEntity findByEmail(String email);
    SignUpEntity findBycontactNumber(long contactNumber);
    boolean update(SignUpDto dto);
    boolean deleteByEmail(String email);
    public boolean softDeleteByEmail(String email);
    boolean updateOtpByEmail(String email, String otp);
    boolean updatePasswordByEmail(String email, String newPassword);

}
