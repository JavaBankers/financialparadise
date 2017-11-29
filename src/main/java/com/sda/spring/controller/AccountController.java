package com.sda.spring.controller;

import com.sda.spring.dto.AccountDto;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAccount(@ModelAttribute String firstName,
                             @ModelAttribute String lastName,
                             @ModelAttribute String pesel,
                             @ModelAttribute String email,
                             @ModelAttribute String password){

        accountService.addAccount(firstName, lastName, pesel, email, password);
        return "New account has been added";
    }

    @PostMapping("/account")
    public ResponseEntity<String> create(@RequestBody AccountDto account){
        //todo accountService.create(account);

        return null;
    }
}