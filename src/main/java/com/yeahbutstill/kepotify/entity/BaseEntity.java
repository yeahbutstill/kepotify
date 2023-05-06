package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    @CreatedBy
//    private String createdBy;
//
//    @LastModifiedBy
//    private String updatedBy;
//
//    @CreatedDate
//    private LocalDateTime created;
//
//    @LastModifiedDate
//    private LocalDateTime updated;
//
//    @NotBlank
//    @NotEmpty
//    private String environment;
//
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private StatusRecord statusRecord = StatusRecord.ACTIVE;

}