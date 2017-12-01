package com.sda.spring;

import com.sda.spring.entity.Account;
import com.sda.spring.entity.SuspiciousPerson;
import com.sda.spring.entity.TransferHistory;
import com.sda.spring.repository.SuspiciousPersonRepository;
import com.sda.spring.repository.TransferHistoryRepository;
import com.sda.spring.service.TransferHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class SuspiciousTransferChecker {

    private SuspiciousPersonRepository suspiciousPersonRepository;

    private TransferHistoryService transferHistoryService;

    @Autowired
    public SuspiciousTransferChecker(SuspiciousPersonRepository suspiciousPersonRepository, TransferHistoryService transferHistoryService) {
        this.suspiciousPersonRepository = suspiciousPersonRepository;
        this.transferHistoryService = transferHistoryService;
    }

    public Boolean isTransferSuspicious(Account accountFrom, Account accountTo, BigDecimal moneyToTransfer) {

        SuspiciousPerson suspiciousPersonFrom = null;
        SuspiciousPerson suspiciousPersonTo = null;

        try {
            suspiciousPersonFrom = suspiciousPersonRepository
                    .findSuspiciousPersonByFirstNameAndLastNameAndPesel(accountFrom.getCustomer().getFirstName(),
                            accountFrom.getCustomer().getLastName(), accountFrom.getCustomer().getPesel());
            suspiciousPersonTo = suspiciousPersonRepository
                    .findSuspiciousPersonByFirstNameAndLastNameAndPesel(accountTo.getCustomer().getFirstName(),
                            accountTo.getCustomer().getLastName(), accountTo.getCustomer().getPesel());
        } catch (NullPointerException e) {
        }

        Boolean isPersonFromSuspicious;
        Boolean isPersonToSuspicious;

        //sprawdzanie czy jedna lub obie osoby sa podejrzane
        //jesli tak to juz mozna zwrocic true
        if (suspiciousPersonFrom != null) {
            isPersonFromSuspicious = true;
        }
        if (suspiciousPersonTo != null) {
            isPersonToSuspicious = true;
        }

        //czy wielkosc przelewu jest podejrzana


        return false;
    }

    //czy transakcja wykonywana nie jest wieksza niz ostatnie z 12 miesiecy
//    private List<Optional<BigDecimal>> getBiggestTransactionsFromLast12Months(String firstName, String lastName) {
//        return Stream
//                .iterate(1, i -> i+1)
//                .limit(12)
//                .map(i -> i % 2 == 0 ? BigDecimal.valueOf(i*1000) : null)
//                .map(Optional::ofNullable)
//                .collect(Collectors.toList());
//    }

    public BigDecimal getBiggestTransferFromLast12Moths(String bankAccountNumber) {
        return getBiggestTransactionFromLast12Months(bankAccountNumber);
    }


    private BigDecimal getBiggestTransactionFromLast12Months(String bankAccountNumber) {

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime limitDate = LocalDateTime.of(currentDate.getYear() - 1, currentDate.getMonth(),
                currentDate.getDayOfMonth(), currentDate.getHour(), currentDate.getMinute(), currentDate.getSecond(),
                currentDate.getNano());

//        List<TransferHistory> transferHistories = transferHistoryService.getAllTransferHistories();
////        List<TransferHistory> transferHistories = new LinkedList<>();
//        List<TransferHistory> foundAccountHistories = new LinkedList<>();
////        Iterable<TransferHistory> iterable = transferHistoryRepository.findAll();
////        iterable.forEach(e -> transferHistories.add(e));
//
//        for (TransferHistory history : transferHistories) {
//            if (history.getBankAccountNumberFrom().equals(bankAccountNumber)) {
//                foundAccountHistories.add(history);
//            }
//        }

        List<TransferHistory> foundAccountHistories = transferHistoryService.
                getTransferHistoryForSpecificAccount(bankAccountNumber);

        List<BigDecimal> transferAmountsFromLast12Months = new LinkedList<>();

        for (TransferHistory history : foundAccountHistories) {
            if (history.getDate().compareTo(limitDate) >= 0) {
                transferAmountsFromLast12Months.add(history.getAmount());
            }
        }

        return transferAmountsFromLast12Months.stream()
                .max(BigDecimal::compareTo)
                .orElseThrow(() -> new RuntimeException("Element not found"));

    }
}
