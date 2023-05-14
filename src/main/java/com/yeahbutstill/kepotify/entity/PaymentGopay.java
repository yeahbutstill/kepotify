package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "payments_gopay")
public class PaymentGopay extends Payment {

    @Column(name = "gopay_id")
    private String gopayId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentGopay gopay = (PaymentGopay) o;
        return getId() != null && Objects.equals(getId(), gopay.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
