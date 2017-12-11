package com.sda.spring.repository;

import com.sda.spring.entity.SuspiciousTransferHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SuspiciousTransferHistoryRepository extends CrudRepository<SuspiciousTransferHistory, Integer> {

    List<SuspiciousTransferHistory> findAll();
}
