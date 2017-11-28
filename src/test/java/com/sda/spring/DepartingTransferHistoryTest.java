package com.sda.spring;

import com.sda.spring.entity.ArrivingTransferHistory;
import com.sda.spring.entity.DepartingTransferHistory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DepartingTransferHistoryTest {

    private DepartingTransferHistory testedObject;

    private final static Integer ID = 89;
    private final static String ACCOUNT_NO = "02456798712355698666677772";
    private final static Integer CUSTOMER_ID = 78954623;
    private final static LocalDateTime DATETIME = LocalDateTime.of(2016,4,9,7,0);
    private final static BigDecimal AMOUNT = new BigDecimal("564987.24");

    @Before
    public void setUp() throws Exception {
        testedObject = new DepartingTransferHistory();
        testedObject.setId(ID);
        testedObject.setBankAccountNumber(ACCOUNT_NO);
        testedObject.setCustomerId(CUSTOMER_ID);
        testedObject.setDate(DATETIME);
        testedObject.setAmount(AMOUNT);
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getId()).isEqualTo(89);
        assertThat(testedObject.getBankAccountNumber()).isEqualTo("02456798712355698666677772");
        assertThat(testedObject.getCustomerId()).isEqualTo(78954623);
        assertThat(testedObject.getDate()).isEqualTo(LocalDateTime.of(2016,4,9,7,0));
        assertThat(testedObject.getAmount()).isEqualTo(new BigDecimal("564987.24"));
    }
}
