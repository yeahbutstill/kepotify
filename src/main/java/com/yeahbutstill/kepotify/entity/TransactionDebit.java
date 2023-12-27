package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "transactions_debit")
@SQLDelete(sql = "UPDATE transactions_debit SET status_record='INACTIVE' WHERE id=?")
@SQLRestriction(value = "status_record='ACTIVE'")
public class TransactionDebit extends Transaction{

    @Column(name = "debit_amount")
    private BigDecimal debitAmount;

}
