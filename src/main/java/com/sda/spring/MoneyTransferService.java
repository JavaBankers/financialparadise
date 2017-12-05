package com.sda.spring;

import com.sda.spring.entity.Customer;
import com.sda.spring.entity.SuspiciousTransferHistory;
import com.sda.spring.entity.TransferHistory;
import com.sda.spring.repository.SuspiciousPersonRepository;
import com.sda.spring.repository.SuspiciousTransferHistoryRepository;
import com.sda.spring.repository.TransferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MoneyTransferService {

    private final static BigDecimal BALANCE_LIMIT = BigDecimal.ZERO;

    private TransferChecker transferChecker;

    private TransferHistoryRepository transferHistoryRepository;

    private SuspiciousTransferHistoryRepository suspiciousTransferHistoryRepository;

    @Autowired
    public MoneyTransferService(SuspiciousTransferHistoryRepository suspiciousTransferHistoryRepository,
                                TransferChecker transferChecker, TransferHistoryRepository transferHistoryRepository) {
        this.suspiciousTransferHistoryRepository = suspiciousTransferHistoryRepository;
        this.transferChecker = transferChecker;
        this.transferHistoryRepository = transferHistoryRepository;
    }

    public void transferMoney(Customer customerSender, Customer customerReciever, BigDecimal moneyToTransfer) {

        TransferHistory transferHistory;
        SuspiciousTransferHistory suspiciousTransferHistory;
        Boolean transferIsSuspicious = transferChecker.isTransferSuspicious(customerSender, customerReciever, moneyToTransfer);

        if (transferIsSuspicious) {
            suspiciousTransferHistory = new SuspiciousTransferHistory.Builder()
                    .withAmount(moneyToTransfer)
                    .withBankAccountNumberFrom(customerSender.getAccount().getBankAccountNumber())
                    .withBankAccountNumberTo(customerReciever.getAccount().getBankAccountNumber())
                    .withDate(LocalDateTime.now())
                    .build();

            suspiciousTransferHistoryRepository.save(suspiciousTransferHistory);
        }

        if (moneyToTransfer.compareTo(customerSender.getAccount().getBalance()) < 1 && customerSender.getAccount()
                .getBalance().compareTo(BALANCE_LIMIT) >= 0) {
            customerSender.getAccount().setBalance(customerSender.getAccount().getBalance().subtract(moneyToTransfer));
            customerReciever.getAccount().setBalance(customerReciever.getAccount().getBalance().add(moneyToTransfer));
            transferHistory = new TransferHistory(
                    customerSender.getAccount().getBankAccountNumber(),
                    customerReciever.getAccount().getBankAccountNumber(),
                    LocalDateTime.now(),
                    moneyToTransfer);
            transferHistoryRepository.save(transferHistory);
        } else {
            throw new InsufficientFundsException();
        }
    }
}

