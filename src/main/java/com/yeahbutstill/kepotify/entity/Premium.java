package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.*;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@DiscriminatorValue("PREMIUM")
public class Premium extends Users {

    @Transient
    private BigDecimal hargaPremeium;

    @Column(name = "member_expired_date")
    private LocalDateTime memberExpiredDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Premium premium = (Premium) o;
        return getId() != null && Objects.equals(getId(), premium.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
