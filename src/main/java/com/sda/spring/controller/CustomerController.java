package com.sda.spring.controller;

import com.sda.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/")
    public String mainPage(){
        return "mainPage";
    }
}
