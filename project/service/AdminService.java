package com.xworkz.project.service;

import com.xworkz.project.dto.SignUpDto;

import java.util.List;

public interface AdminService {
    public boolean generateAndSaveOtp(String email);
    public boolean verifyOtp(String email, int otp);
    public boolean getByEmail(String email);
    List<SignUpDto> viewUsers();
}
