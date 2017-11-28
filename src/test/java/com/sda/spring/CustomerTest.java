package com.sda.spring;

import com.sda.spring.entity.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    private Customer testedObject;

    private final static Integer ID = 69;
    private final static String FIRSTNAME = "Andrzej";
    private final static String LASTNAME = "Gablota";
    private final static String PESEL = "67112456789";
    private final static String USERNAME = "andrew";
    private final static String PASSWORD = "isthebest";

    @Before
    public void setUp() throws Exception {
        testedObject = new Customer();
        testedObject.setId(ID);
        testedObject.setFirstname(FIRSTNAME);
        testedObject.setLastname(LASTNAME);
        testedObject.setPesel(PESEL);
        testedObject.setUsername(USERNAME);
        testedObject.setPassword(PASSWORD);
    }

    @Test
    public void shouldCreateObject() throws Exception {
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getId()).isEqualTo(69);
        assertThat(testedObject.getFirstname()).isEqualTo("Andrzej");
        assertThat(testedObject.getLastname()).isEqualTo("Gablota");
        assertThat(testedObject.getPesel()).isEqualTo("67112456789");
        assertThat(testedObject.getUsername()).isEqualTo("andrew");
        assertThat(testedObject.getPassword()).isEqualTo("isthebest");
    }
}
