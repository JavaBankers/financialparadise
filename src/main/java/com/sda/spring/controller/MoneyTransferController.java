package com.sda.spring.controller;

import com.sda.spring.dto.CustomerWithTransferReceiver;
import com.sda.spring.entity.Customer;
import com.sda.spring.service.CustomerService;
import com.sda.spring.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoneyTransferController {

    private static final String TRANSFER_URL = "/v1/transfer";

    MoneyTransferService moneyTransferService;

    CustomerService customerService;

    @Autowired
    public MoneyTransferController(MoneyTransferService moneyTransferService, CustomerService customerService) {
        this.moneyTransferService = moneyTransferService;
        this.customerService = customerService;
    }

    @PostMapping(value = TRANSFER_URL)
    public ResponseEntity<?> sendMoney(@RequestBody CustomerWithTransferReceiver customerWithTransferReceiver) {
        Customer customerFrom = customerWithTransferReceiver.getCustomer();
        Customer customerTo = customerService.getCustomerByAccountNumber(customerWithTransferReceiver.getTransferReceiver().getBankAccountNumber());
        moneyTransferService.transferMoney(customerFrom, customerTo, customerWithTransferReceiver.getTransferReceiver().getAmount(),
                customerWithTransferReceiver.getTransferReceiver().getTitle());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
