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

    @Transactional
    public void addAccount(String firstName,
                           String lastName,
                           String pesel,
                           String email,
                           String password) {

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPesel(pesel);
        customer.setEmail(email);
        customer.setPassword(password);
        Account account = new Account();
        account.setBalance(new BigDecimal("0.00"));
        account.setCurrency(Currency.PLN);
        account.setCustomer(customer);

        accountRepository.save(account);
    }

    @Transactional
    public Account getAccountById(Integer id) {
        return accountRepository.findAccountById(id);
    }

    @Transactional
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        Iterable<Account> iterable = accountRepository.findAll();
        iterable.forEach(e -> accounts.add(e));
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
