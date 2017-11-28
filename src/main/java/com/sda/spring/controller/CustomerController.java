package com.sda.spring.controller;

import com.sda.spring.entity.Customer;
import com.sda.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/")
    public String mainPage(){
        return "mainPage";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute Customer customer, ModelMap modelMap){
        customerService.addCustomer(customer);
        return "success";
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }
}
