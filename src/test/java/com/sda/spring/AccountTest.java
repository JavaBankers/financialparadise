package com.sda.spring;

import com.sda.spring.entity.Account;
import com.sda.spring.entity.Customer;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    private Account testedObject;

    @Before
    public void setUp()throws Exception{
        testedObject = new Account();
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
    }
}
