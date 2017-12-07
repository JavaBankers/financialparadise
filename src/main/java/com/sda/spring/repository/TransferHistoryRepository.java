package com.sda.spring.repository;

import com.sda.spring.entity.TransferHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransferHistoryRepository extends CrudRepository<TransferHistory, Integer> {

    List<TransferHistory> findTransferHistoriesByBankAccountNumberFromOrderByDateDesc(String bankAccountNumber);

}
