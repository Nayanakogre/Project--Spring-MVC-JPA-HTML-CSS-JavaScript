package com.xworkz.project.service;

import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.entity.SignUpEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface SignUpService {
    boolean save(SignUpDto signUpDto);
    boolean findByEmail(String email);
    boolean findByContact(long contactNumber);
    SignUpDto findDtoByEmail(String email);
    boolean updateUser(SignUpDto dto);
    boolean deleteByEmail(String email);
    public boolean softDeleteByEmail(String email);
    public boolean generateAndSendOtp(String email);
    public boolean isOtpValid(String email, String otp);
    boolean updatePassword(String email, String newPassword);
    public void updateImageByEmail(String email, byte[] imageBytes, String imageName, String profileName);
    public SignUpEntity downloadImage(String email);


}
