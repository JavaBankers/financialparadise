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

    @Transactional
    public void addCustomer(String firstName,
                            String lastName,
                            String pesel,
                            String email,
                            String password){

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPesel(pesel);
        customer.setEmail(email);
        customer.setPassword(password);

        customerRepository.save(customer);
    }

    @Transactional
    public Customer getCustomerById(Integer id){
        return customerRepository.findCustomerById(id);
    }

    @Transactional
    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        Iterable<Customer> iterable = customerRepository.findAll();
        iterable.forEach(e -> customers.add(e));
        return customers;
    }

    @Transactional
    public void updateCustomer(String firstName,
                               String lastName,
                               String pesel,
                               String email,
                               String password){
        addCustomer(firstName,lastName,pesel,email,password);
    }

    @Transactional
    public void deleteCustomerById(Integer id){
        customerRepository.deleteCustomerById(id);
    }
}
