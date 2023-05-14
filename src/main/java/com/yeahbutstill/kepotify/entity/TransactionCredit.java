package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "transactions_credit")
@SQLDelete(sql = "UPDATE transactions_credit SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class TransactionCredit extends Transaction {

    @Column(name = "credit_amount")
    private BigDecimal creditAmount;

}
