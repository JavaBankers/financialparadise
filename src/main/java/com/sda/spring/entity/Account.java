package com.sda.spring.entity;

import com.sda.spring.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    private Integer id;
    private String bankAccountNumber;
    private BigDecimal balance;
    private Currency currency = Currency.PLN;

    @OneToOne
    private Customer customer;

    public Account() {
    }

    public Account(String bankAccountNumber, BigDecimal balance, Currency currency, Customer customer) {
        this.bankAccountNumber = bankAccountNumber;
        this.balance = balance;
        this.currency = currency;
        this.customer = customer;
    }
}
