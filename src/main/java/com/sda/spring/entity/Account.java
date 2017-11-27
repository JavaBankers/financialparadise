package com.sda.spring.entity;

import com.sda.spring.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal balance;
    private Currency currency;

    @OneToOne
    private Customer customer;

    public Account() {
    }

    public Account(BigDecimal balance, Currency currency, Customer customer) {
        this.balance = balance;
        this.currency = currency;
        this.customer = customer;
    }
}
