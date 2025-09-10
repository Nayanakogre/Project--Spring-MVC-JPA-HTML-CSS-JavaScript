package com.xworkz.project.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SignUpDto extends AuditDto{

    private Integer id;
    private String userName;
    private Long contactNumber;
    private Long alternateContactNumber;
    private String email;
    private String password;
    private String adressLine1;
    private String adressLine2;
    private String city;
    private String state;
    private int pin;
    private Boolean isActive=true;
    private Integer otp;
    private MultipartFile image;
    private String imageName;
    //private MultipartFile multipartFile;
    private String profile;

}
