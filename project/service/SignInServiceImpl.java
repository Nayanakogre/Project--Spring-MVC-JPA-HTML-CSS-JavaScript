package com.xworkz.project.service;


import com.xworkz.project.entity.SignUpEntity;
import com.xworkz.project.repository.SignUpRepo;
import com.xworkz.project.util.DecryptUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class SignInServiceImpl implements SignInService{
    private SignUpRepo signUpRepo;

    @Override
    public boolean signIn(String email, String inputPassword) {
        SignUpEntity entity = signUpRepo.findByEmail(email);

        if (entity == null) {
            System.out.println("Email not found");
            return false;
        }
        try {
            String decryptedPassword = DecryptUtil.decrypt(entity.getPassword());
            if (decryptedPassword != null && decryptedPassword.equals(inputPassword)) {
                System.out.println("Login successful");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Invalid password");
        return false;

    }

}
