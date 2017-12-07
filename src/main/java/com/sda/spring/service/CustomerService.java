package com.sda.spring.service;

import com.sda.spring.entity.Customer;
import com.sda.spring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void save(Customer customer){
        customerRepository.save(customer);
    }

    @Transactional
    public Customer getCustomerById(Integer id){
        return customerRepository.findCustomerById(id);
    }

    @Transactional
    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(e -> customers.add(e));
        return customers;
    }

    @Transactional
    public Customer getCustomerByAccountNumber(String accountNumber){
        return customerRepository.findCustomerByAccount_BankAccountNumber(accountNumber);
    }

    @Transactional
    public Customer getCustomerByEmail(String email){
        return customerRepository.findCustomerByEmail(email);
    }

    @Transactional
    public void deleteCustomerById(Integer id){
        customerRepository.deleteCustomerById(id);
    }
}
