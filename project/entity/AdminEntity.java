package com.xworkz.project.entity;

import com.xworkz.project.dto.AuditDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="admin_details")


@NamedQuery(
        name = "AdminEntity.findByEmail",
        query = "SELECT a FROM AdminEntity a WHERE a.email = :email"
)


public class AdminEntity extends AuditDto {
    @Id
    @Column(name = "a_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="s_username")
    private String userName;

    @Column(name="s_email")
    private String email;

    @Column(name="s_contactNumber")
    private long contactNumber;

    @Column(name="s_otp")
    private Integer otp;

    @Column(name="s_isActive")
    private Boolean isActive;


}
