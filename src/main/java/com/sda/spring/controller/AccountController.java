package com.sda.spring.controller;

import com.sda.spring.entity.Account;
import com.sda.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/find/all")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String addAccount(@ModelAttribute("firstName") String firstName,
//                             @ModelAttribute("lastName") String lastName,
//                             @ModelAttribute("pesel") String pesel,
//                             @ModelAttribute("email") String email,
//                             @ModelAttribute("password") String password){
//
//        accountService.addAccount(firstName, lastName, pesel, email, password);
//        return "New account has been added";
//    }


}