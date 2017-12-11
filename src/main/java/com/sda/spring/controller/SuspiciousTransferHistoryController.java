package com.sda.spring.controller;

import com.sda.spring.entity.SuspiciousTransferHistory;
import com.sda.spring.repository.SuspiciousTransferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SuspiciousTransferHistoryController {

    private static final String CUSTOMER_URL = "/v1/customer/suspicious";

    private SuspiciousTransferHistoryRepository suspiciousTransferHistoryRepository;

    @Autowired
    public SuspiciousTransferHistoryController(SuspiciousTransferHistoryRepository suspiciousTransferHistoryRepository) {
        this.suspiciousTransferHistoryRepository = suspiciousTransferHistoryRepository;
    }

    @GetMapping(CUSTOMER_URL + "/list")
    public List<SuspiciousTransferHistory> getAllsuspiciousTransferHistory(){
        return suspiciousTransferHistoryRepository.findAll();
    }
}
