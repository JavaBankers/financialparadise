package com.sda.spring;

import com.sda.spring.controller.CustomerController;
import com.sda.spring.entity.Customer;
import com.sda.spring.service.AccountService;
import com.sda.spring.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CustomerControllerTest {

    private CustomerController testedObject;

    @Mock
    private CustomerService mockedCustomerService;

    @Mock
    private AccountService mockedAccountService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        testedObject = new CustomerController(mockedCustomerService, mockedAccountService);
    }

    @Test
    public void shouldReturnCustomersList() throws Exception {

        when(testedObject.getAllCustomers()).thenReturn(getCustomersList());

        List<Customer> result = testedObject.getAllCustomers();

        verify(mockedCustomerService, times(1)).getAllCustomers();
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    public void shouldReturnSpecificCustomerWhenIdPassed() throws Exception {

        Customer desiredCustomer = new Customer();
        desiredCustomer.setId(5);

        when(testedObject.getCustomerById(5)).thenReturn(desiredCustomer);

        Customer foundCustomer = testedObject.getCustomerById(5);

        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer).isEqualTo(desiredCustomer);
    }

    @Test
    public void shouldAddCustomer() throws Exception {

        ResponseEntity<String> desiredResponse = new ResponseEntity<>("New customer has been added", HttpStatus.CREATED);
        Customer customerToAdd = new Customer();

        when(testedObject.addCustomer(customerToAdd)).thenReturn(desiredResponse);

        ResponseEntity<String> response = testedObject.addCustomer(customerToAdd);

        assertThat(response).isEqualTo(desiredResponse);
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {

        Customer customer = new Customer();
        customer.setId(69);
        ResponseEntity<?> desiredResponse = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        doNothing().when(mockedCustomerService).deleteCustomerById(69);

        ResponseEntity<?> response = testedObject.deleteCustomer(69);

        assertThat(response).isEqualTo(desiredResponse);
        verify(mockedCustomerService,times(1)).deleteCustomerById(69);


    }

    private List<Customer> getCustomersList(){
        List<Customer> result = new ArrayList<>();
        Customer c1 = new Customer();
        Customer c2 = new Customer();
        Customer c3 = new Customer();
        Customer c4 = new Customer();
        result.add(c1);
        result.add(c2);
        result.add(c3);
        result.add(c4);
        return result;
    }
}
