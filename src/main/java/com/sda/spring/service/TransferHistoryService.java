package com.sda.spring.service;

import com.sda.spring.entity.TransferHistory;
import com.sda.spring.repository.TransferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferHistoryService {

    @Autowired
    private TransferHistoryRepository transferHistoryRepository = null;

    public TransferHistoryService(TransferHistoryRepository transferHistoryRepository) {
        this.transferHistoryRepository = transferHistoryRepository;
    }

    public List<TransferHistory> getAllTransferHistories(){
//        List<TransferHistory> transferHistories = new LinkedList<>();
//        transferHistoryRepository.findAll().forEach(e -> transferHistories.add(e));
//        return transferHistories;
        return (List<TransferHistory>) transferHistoryRepository.findAll();
    }

    public void addTransferHistory(TransferHistory transferHistory){
        transferHistoryRepository.save(transferHistory);
    }

    public List<TransferHistory> getTransferHistoryForSpecificAccount(String bankAccountNumber){
        return transferHistoryRepository.findTransferHistoriesByBankAccountNumberFromOrderByDateDesc(bankAccountNumber);
    }
}
