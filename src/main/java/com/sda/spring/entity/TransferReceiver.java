package com.sda.spring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class TransferReceiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String bankAccountNumber;
    private String title;
    private BigDecimal amount;

    public TransferReceiver() {}

}
