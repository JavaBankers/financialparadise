package com.sda.spring.controller;

import com.sda.spring.entity.Customer;
import com.sda.spring.service.AccountService;
import com.sda.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private static final String CUSTOMER_URL = "/v1/customer";

    private CustomerService customerService;

    private AccountService accountService;

    @Autowired
    public CustomerController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping(CUSTOMER_URL + "/list")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(CUSTOMER_URL + "/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping(value = CUSTOMER_URL)
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        accountService.save(customer.getAccount());
        customerService.save(customer);
        return new ResponseEntity<>("New customer has been added", HttpStatus.CREATED);
    }

    @PutMapping(value = CUSTOMER_URL)
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        accountService.save(customer.getAccount());
        customerService.save(customer);
        return new ResponseEntity<>("Customer has been updated", HttpStatus.OK);
    }

    @DeleteMapping(value = CUSTOMER_URL + "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(CUSTOMER_URL + "/send" + "{email}")
    public Customer getCustomerByEmail(@PathVariable("email") String email) {
        return customerService.getCustomerByEmail(email);
    }
}
