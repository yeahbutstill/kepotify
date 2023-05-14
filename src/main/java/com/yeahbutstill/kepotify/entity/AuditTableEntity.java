package com.yeahbutstill.kepotify.entity;

import com.yeahbutstill.kepotify.enums.EnvironmentType;
import com.yeahbutstill.kepotify.enums.StatusRecord;
import com.yeahbutstill.kepotify.listener.UpdatedCreatedAtAware;
import com.yeahbutstill.kepotify.listener.impl.UpdatedCreatedAtListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

// Ini hanya sebuah parent class tapi bukan IS-A Relationship
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({
        AuditingEntityListener.class,
        UpdatedCreatedAtListener.class
})
// ini dimana T itu harus extends Serializable
public abstract class AuditTableEntity<T extends Serializable> implements UpdatedCreatedAtAware {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private T id;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnvironmentType environment = EnvironmentType.SIT;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_record")
    private StatusRecord statusRecord = StatusRecord.ACTIVE;

}