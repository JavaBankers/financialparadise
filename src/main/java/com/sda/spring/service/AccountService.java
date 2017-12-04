package com.sda.spring.service;

import com.sda.spring.Currency;
import com.sda.spring.entity.Account;
import com.sda.spring.entity.Customer;
import com.sda.spring.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

//    @Transactional
//    public void addAccount(String firstName,
//                           String lastName,
//                           String pesel,
//                           String email,
//                           String password) {
//
//        Customer customer = new Customer(firstName, lastName, pesel, email, password);
//
//        accountRepository.save(account);
//    }

    @Transactional
    public Account getAccountById(Integer id) {
        return accountRepository.findAccountById(id);
    }

    @Transactional
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(e -> accounts.add(e));
        return accounts;
    }

    @Transactional
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    @Transactional
    public void deleteAccountById(Integer id) {
        accountRepository.deleteAccountById(id);
    }
}
