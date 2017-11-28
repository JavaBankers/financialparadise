package com.sda.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

    @RequestMapping("/")
    public String mainPage(){
        return "mainPage";
    }
}
