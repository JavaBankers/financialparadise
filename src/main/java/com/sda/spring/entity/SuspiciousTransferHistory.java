package com.sda.spring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class SuspiciousTransferHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bankAccountNumberFrom;
    private String bankAccountNumberTo;
    private LocalDateTime date;
    private BigDecimal amount;
    private Boolean amountSuspicious;
    private Boolean personFromBlackList;

    public SuspiciousTransferHistory() {
    }

    public SuspiciousTransferHistory(String bankAccountNumberFrom, String bankAccountNumberTo, LocalDateTime date,
                                     BigDecimal amount, Boolean amountSuspicious, Boolean personFromBlackList) {
        this.bankAccountNumberFrom = bankAccountNumberFrom;
        this.bankAccountNumberTo = bankAccountNumberTo;
        this.date = date;
        this.amount = amount;
        this.amountSuspicious = amountSuspicious;
        this.personFromBlackList = personFromBlackList;
    }
}


