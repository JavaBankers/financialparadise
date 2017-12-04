package com.sda.spring;

import com.sda.spring.entity.Account;
import com.sda.spring.entity.Customer;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    private Account testedObject;

    private final static Integer ID = 1;
    private final static BigDecimal BALANCE = new BigDecimal("1579.84");
    private final static Currency CURRENCY = Currency.PLN;
    private final static String FIRSTNAME = "Tom";
    private final static String LASTNAME = "Bom";
    private final static String PESEL = "78120644556";
    private final static String EMAIL = "thomas123@gmail.com";
    private final static String PASSWORD = "thomas456";

//    @Before
//    public void setUp()throws Exception{
//        testedObject = new Account();
//        testedObject.setId(ID);
//        testedObject.setBalance(BALANCE);
//        testedObject.setCurrency(CURRENCY);
//        testedObject.setCustomer(new Customer(FIRSTNAME, LASTNAME, PESEL, EMAIL, PASSWORD));
//    }

    @Test
    public void shouldCreateObject() throws Exception {
//        assertThat(testedObject).isNotNull();
//        assertThat(testedObject.getId()).isEqualTo(1);
//        assertThat(testedObject.getBalance()).isEqualTo(new BigDecimal("1579.84"));
//        assertThat(testedObject.getCurrency()).isEqualTo(Currency.PLN);
//        assertThat(testedObject.getCustomer().getFirstName()).isEqualTo("Tom");
//        assertThat(testedObject.getCustomer().getLastName()).isEqualTo("Bom");
//        assertThat(testedObject.getCustomer().getPesel()).isEqualTo("78120644556");
//        assertThat(testedObject.getCustomer().getEmail()).isEqualTo("thomas123@gmail.com");
//        assertThat(testedObject.getCustomer().getPassword()).isEqualTo("thomas456");
    }
}
