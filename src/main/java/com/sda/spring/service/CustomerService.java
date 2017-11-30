package com.sda.spring.service;

import com.sda.spring.entity.Customer;
import com.sda.spring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void addCustomer(Customer customer){
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

//    @Transactional
//    public void updateCustomer(String firstName,
//                               String lastName,
//                               String pesel,
//                               String email,
//                               String password){
//        addCustomer(firstName,lastName,pesel,email,password);
//    }

    @Transactional
    public void deleteCustomerById(Integer id){
        customerRepository.deleteCustomerById(id);
    }
}
