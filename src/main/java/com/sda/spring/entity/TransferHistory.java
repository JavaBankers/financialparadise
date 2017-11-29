package com.sda.spring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transfer_history")
public class TransferHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bank_account_number_from")
    private String bankAccountNumberFrom;

    @Column(name = "bank_account_number_to")
    private String bankAccountNumberTo;

    private LocalDateTime date;

    private BigDecimal amount;

    public TransferHistory() {
    }

    public TransferHistory(String bankAccountNumberFrom, String bankAccountNumberTo, LocalDateTime date, BigDecimal amount) {
        this.bankAccountNumberFrom = bankAccountNumberFrom;
        this.bankAccountNumberTo = bankAccountNumberTo;
        this.date = date;
        this.amount = amount;
    }
}
