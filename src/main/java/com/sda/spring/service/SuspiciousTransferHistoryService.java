package com.sda.spring.service;

import com.sda.spring.entity.SuspiciousTransferHistory;
import com.sda.spring.repository.SuspiciousTransferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuspiciousTransferHistoryService {

    SuspiciousTransferHistoryRepository suspiciousTransferHistoryRepository;

    @Autowired
    public SuspiciousTransferHistoryService(SuspiciousTransferHistoryRepository suspiciousTransferHistoryRepository) {
        this.suspiciousTransferHistoryRepository = suspiciousTransferHistoryRepository;
    }

    public List<SuspiciousTransferHistory> getAllSuspiciousTransferHistory() {
        return suspiciousTransferHistoryRepository.findAll();
    }
}
