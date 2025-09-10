package com.xworkz.project.dto;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass

public abstract class AuditDto {

    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "createdAt")
    private LocalDateTime createdAt =LocalDateTime.now();
    @Column(name = "updatedBy")
    private String updatedBy="System";
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt=LocalDateTime.now();
}
