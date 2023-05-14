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
@Table(name = "payments_credit_card")
public class PaymentCreditCard extends Payment {

    @Column(name = "masked_card")
    private String maskedCard;

    @Column(name = "bank")
    private String bank;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentCreditCard that = (PaymentCreditCard) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
