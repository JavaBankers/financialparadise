package com.sda.spring.controller;

import com.sda.spring.entity.TransferHistory;
import com.sda.spring.service.TransferHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransferHistoryController {

    private static final String HISTORY_URL = "/v1/customer/history";

    @Autowired
    TransferHistoryService transferHistoryService;

    @GetMapping(HISTORY_URL + "/list" + "{accountNumber}")
    public List<TransferHistory> getAllTransferHistoryForSpecificAccount(@PathVariable("accountNumber")String accountNumber) {
        return transferHistoryService.getTransferHistoryForSpecificAccount(accountNumber);
    }
}
