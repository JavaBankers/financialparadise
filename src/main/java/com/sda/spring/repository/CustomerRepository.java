package com.sda.spring.repository;

import com.sda.spring.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    void deleteCustomerById(Integer id);
    Customer findCustomerById(Integer id);
    Customer findCustomerByAccount_BankAccountNumber(String accountNumber);
    Customer findCustomerByEmail(String email);
}
