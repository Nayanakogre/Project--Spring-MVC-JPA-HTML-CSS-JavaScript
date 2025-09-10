package com.xworkz.project.entity;

import com.xworkz.project.dto.AuditDto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Data
@Table(name="signup_deatils")
@NamedQueries({
        @NamedQuery(
                name = "signin",
                query = "SELECT s FROM SignUpEntity s WHERE s.email = :email"
        ),
        @NamedQuery(
                name = "checkContact",
                query = "SELECT s FROM SignUpEntity s WHERE s.contactNumber = :contactNumber"
        ),
        @NamedQuery(
                name = "update",
                query = "UPDATE SignUpEntity s SET s.userName = :userName, s.contactNumber = :contactNumber, s.alternateContactNumber = :alternateContactNumber, s.adressLine1 = :adressLine1, s.adressLine2 = :adressLine2, s.city = :city, s.state = :state, s.pin = :pin WHERE s.email = :email"
        ),
        @NamedQuery(
                name="viewUser",
                query = "SELECT s FROM SignUpEntity s"
        ),
        @NamedQuery(
                name="deleteByEmail",
                query = "DELETE FROM SignUpEntity entity WHERE entity.email= :email"
        ),
        @NamedQuery(
                name = "softDeleteByEmail",
                query = "UPDATE SignUpEntity e SET e.updatedAt = CURRENT_TIMESTAMP WHERE e.email = :email"
        ),
        @NamedQuery(
                name = "updateOtpByEmail",
                query = "UPDATE SignUpEntity s SET s.otp = :otp WHERE s.email = :email"
        ),
        @NamedQuery(
                name = "updateOtp",
                query = "UPDATE SignUpEntity e SET e.otp = :otp, e.updatedAt = :time WHERE e.email = :email"
        ),
        @NamedQuery(
                name = "updatePasswordByEmail",
                query = "UPDATE SignUpEntity e SET e.password = :password WHERE e.email = :email"
        )
})







public class SignUpEntity extends AuditDto {
    @Id
    @Column(name = "s_id")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    @Column(name="s_username")
    private String userName;
    @Column(name="s_contactNumber")
    private long contactNumber;
    @Column(name="s_alternateContactNumber")
    private long alternateContactNumber;
    @Column(name="s_email")
    private String email;
    @Column(name = "s_password")
    private String password;
    @Column(name="s_adressLine1")
    private String adressLine1;
    @Column(name = "s_adressLine2")
    private String adressLine2;
    @Column(name="s_city")
    private String city;
    @Column(name = "s_state")
    private String state;
    @Column(name="s_pin")
    private int pin;
    @Column(name="s_isActive")
    private Boolean isActive;
    @Column(name="s_otp")
    private Integer otp;

    @Lob
    @Column(name = "s_profile_image")
    private byte[] profileImage;
    @Column(name = "s_imageName")
    private String imageName;
    @Column(name="s_profile")
    private String profile;
}
