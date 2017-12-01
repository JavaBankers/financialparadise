package com.sda.spring.controller;

import com.sda.spring.entity.Customer;
import com.sda.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private static final String CUSTOMER_URL = "/v1/customer";

    @Autowired
    private CustomerService customerService;

    @GetMapping("/v1/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(CUSTOMER_URL + "/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer id) {
        return customerService.getCustomerById(id);
    }

//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String updateCustomer(@ModelAttribute("firstName") String firstName,
//                                 @ModelAttribute("lastName") String lastName,
//                                 @ModelAttribute("pesel") String pesel,
//                                 @ModelAttribute("email") String email,
//                                 @ModelAttribute("password") String password){
//
//        customerService.updateCustomer(firstName,lastName,pesel,email,password);
//        return "Customer has been updated";
//    }


    @PostMapping(value = CUSTOMER_URL)
    public String addCustomer(@RequestBody Customer customer) {
//        Customer customer = new Customer(firstName, lastName, pesel, email, password);
        customerService.save(customer);
        return "New customer has been added";
    }

    @PutMapping(value = CUSTOMER_URL)
    public String updateCustomer(@RequestBody Customer customer) {
//        Customer customer = new Customer(firstName, lastName, pesel, email, password);
        customerService.save(customer);
        return "New customer has been added";
    }
}
