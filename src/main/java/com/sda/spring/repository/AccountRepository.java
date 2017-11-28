package com.sda.spring.repository;

import com.sda.spring.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    void deleteAccountById(Integer id);
    Account findAccountById(Integer id);
}
