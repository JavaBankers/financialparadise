package com.sda.spring.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sda.spring.Currency;

import java.math.BigDecimal;

// odseparowywuje rzeczy bazodanowe
public class AccountDto {

    @JsonProperty("my_balance")
    private BigDecimal balance;
    private Currency currency;
}
