package com.sda.spring.service;

import com.sda.spring.entity.Account;
import com.sda.spring.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public Account getAccountById(Integer id) {
        return accountRepository.findAccountById(id);
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        Iterable<Account> iterable = accountRepository.findAll();
        iterable.forEach(e -> accounts.add(e));
        return accounts;
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccountById(Integer id) {
        accountRepository.deleteAccountById(id);
    }
}
