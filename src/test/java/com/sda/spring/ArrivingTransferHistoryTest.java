package com.sda.spring;

import com.sda.spring.entity.ArrivingTransferHistory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrivingTransferHistoryTest {

    private ArrivingTransferHistory testedObject;

    private final static Integer ID = 15;
    private final static String ACCOUNT_NO = "01222233334444555566667777";
    private final static Integer CUSTOMER_ID = 50333457;
    private final static LocalDateTime DATETIME = LocalDateTime.of(2017,3,29,23,49);
    private final static BigDecimal AMOUNT = new BigDecimal("998567.63");

    @Before
    public void setUp() throws Exception {
        testedObject = new ArrivingTransferHistory();
        testedObject.setId(ID);
        testedObject.setBankAccountNumber(ACCOUNT_NO);
        testedObject.setCustomerId(CUSTOMER_ID);
        testedObject.setDate(DATETIME);
        testedObject.setAmount(AMOUNT);
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getId()).isEqualTo(15);
        assertThat(testedObject.getBankAccountNumber()).isEqualTo("01222233334444555566667777");
        assertThat(testedObject.getCustomerId()).isEqualTo(50333457);
        assertThat(testedObject.getDate()).isEqualTo(LocalDateTime.of(2017,3,29,23,49));
        assertThat(testedObject.getAmount()).isEqualTo(new BigDecimal("998567.63"));
    }
}
