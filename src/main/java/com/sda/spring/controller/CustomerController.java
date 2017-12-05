package com.sda.spring.controller;

import com.sda.spring.entity.Customer;
import com.sda.spring.service.AccountService;
import com.sda.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private static final String CUSTOMER_URL = "/v1/customer";

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @GetMapping(CUSTOMER_URL + "/list")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(CUSTOMER_URL + "/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping(value = CUSTOMER_URL)
    public String addCustomer(@RequestBody Customer customer) {
        accountService.save(customer.getAccount());
        customerService.save(customer);

        return "New customer has been added";
    }

    @PutMapping(value = CUSTOMER_URL)
    public String updateCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        return "Customer has been updated";
    }

    @DeleteMapping(value = CUSTOMER_URL+ "/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomerById(id);
        return "Customer has been deleted";
    }
}
