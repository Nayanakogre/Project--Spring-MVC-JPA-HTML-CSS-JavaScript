package com.xworkz.project.dto;

import lombok.Data;

@Data

public class AdminDto extends AuditDto{
    private Integer id;
    private String username;
    private String email;
    private Long contact;
    private Integer otp;
    private Boolean isActive=true;
}
