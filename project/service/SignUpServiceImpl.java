package com.xworkz.project.service;

import com.xworkz.project.dto.SignUpDto;
import com.xworkz.project.entity.SignUpEntity;
import com.xworkz.project.repository.SignUpRepoImpl;
import com.xworkz.project.util.DecryptUtil;
import com.xworkz.project.util.GenerateOTPUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private SignUpRepoImpl signUpRepo;
    private MailService mailService;
    private static final String IMAGE_DIR = "C:\\Users\\DELL\\Desktop\\Java Ful stack Project\\Images";


    @Override
    public boolean save(SignUpDto signUpDto) {
        SignUpEntity entity = new SignUpEntity();
        System.out.println("signUpDto:" + signUpDto);

        if (signUpDto != null) {
            BeanUtils.copyProperties(signUpDto, entity);
            entity.setIsActive(true);

            System.out.println("entity after copy:" + entity);

            String password = DecryptUtil.encrypt(entity.getPassword());
            System.out.println("Encrypted password:" + password);

            entity.setCreatedBy(entity.getUserName());
            entity.setPassword(password);

            System.out.println("Final entity to save:" + entity);

            boolean saveResult = signUpRepo.save(entity);

            if (saveResult) {
                mailService.sendRegisterEmail(entity.getEmail(), entity.getUserName());
            }

            return saveResult;
        }

        return false;
    }


    @Override

    public boolean findByEmail(String email) {
        if (signUpRepo.findByEmail(email) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean findByContact(long contactNumber) {
        return signUpRepo.findBycontactNumber(contactNumber) != null ? true : false;
    }

    @Override
    public SignUpDto findDtoByEmail(String email) {
        SignUpDto signUpDto = new SignUpDto();
        BeanUtils.copyProperties(signUpRepo.findByEmail(email), signUpDto);

        return signUpDto;
    }

    @Override
    public boolean updateUser(SignUpDto dto) {

        try {
            System.out.println("DTO" + dto);
            return signUpRepo.update(dto);  // Calls repository logic
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteByEmail(String email) {
        if (email != null) {
            SignUpEntity entity = signUpRepo.findByEmail(email); // Add this line
            if (entity != null) {          // Use the getter method
                return signUpRepo.deleteByEmail(email);
            }
        }
        return false;
    }

    @Override
    public boolean softDeleteByEmail(String email) {
        if (email != null && !email.isEmpty()) {
            SignUpEntity entity = signUpRepo.findByEmail(email);
            if (entity != null) {
                entity.setIsActive(false);
                return signUpRepo.softDeleteByEmail(email);
            }
        }
        return false;
    }
    @Override
    public boolean generateAndSendOtp(String email) {
        SignUpEntity entity = signUpRepo.findByEmail(email);
        if (entity != null) {
            String otp = String.valueOf(GenerateOTPUtil.generateOTP());
            // 6-digit string from your util
            System.out.println("otp is:"+otp);
            if (signUpRepo.updateOtpByEmail(email, otp)) {
                mailService.sendRegisterEmailOtp(email, entity.getUserName(), Integer.parseInt(otp));
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isOtpValid(String email, String otp) {
        SignUpEntity entity = signUpRepo.findByEmail(email);

        if (entity != null && entity.getOtp() != null) {
            return entity.getOtp().toString().equals(otp.trim());
        }
        return false;
    }
    public boolean updatePassword(String email, String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            return false;
        }
        String encryptedPassword = DecryptUtil.encrypt(newPassword); // encrypt before save


        return  signUpRepo.updatePasswordByEmail(email, encryptedPassword);

    }


    @Override
    public void updateImageByEmail(String email,
                                   byte[] imageBytes,
                                   String imageName,
                                   String profileName) {

        SignUpEntity entity = signUpRepo.findByEmail(email);

        if (entity == null) {
            throw new IllegalArgumentException("User not found for email: " + email);
        }


        entity.setProfileImage(imageBytes);
        entity.setImageName(imageName);
        entity.setProfile(profileName);

        signUpRepo.save(entity);
    }
    @Override
    public SignUpEntity downloadImage(String email) {
        return signUpRepo.findByEmail(email);
    }



}







