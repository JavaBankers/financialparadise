package com.sda.spring.repository;

import com.sda.spring.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    void deleteCustomerById(Integer id);
    Customer findCustomerById(Integer id);
    Customer findCustomerByEmail(String email);
    Customer findCustomerByAccountBankAccountNumber(String accountNUmber);
}
