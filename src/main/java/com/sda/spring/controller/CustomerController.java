package com.sda.spring.controller;

import com.sda.spring.entity.Customer;
import com.sda.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/find/all")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/find/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer id){
        return customerService.getCustomerById(id);
    }

    @PutMapping(value = "/update")
    public String updateCustomer(@ModelAttribute("firstName") String firstName,
                                 @ModelAttribute("lastName") String lastName,
                                 @ModelAttribute("pesel") String pesel,
                                 @ModelAttribute("email") String email,
                                 @ModelAttribute("password") String password){

        customerService.updateCustomer(firstName,lastName,pesel,email,password);
        return "Customer has been updated";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("firstName") String firstName,
                              @ModelAttribute("lastName") String lastName,
                              @ModelAttribute("pesel") String pesel,
                              @ModelAttribute("email") String email,
                              @ModelAttribute("password") String password){

        customerService.addCustomer(firstName,lastName,pesel,email,password);
        return "New customer has been added";
    }
}
