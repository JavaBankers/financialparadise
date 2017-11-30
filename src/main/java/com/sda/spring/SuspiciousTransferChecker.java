package com.sda.spring;

import com.sda.spring.entity.Account;
import com.sda.spring.entity.SuspiciousPerson;
import com.sda.spring.entity.TransferHistory;
import com.sda.spring.repository.SuspiciousPersonRepository;
import com.sda.spring.repository.TransferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SuspiciousTransferChecker {

    @Autowired
    private SuspiciousPersonRepository suspiciousPersonRepository;

    @Autowired
    private TransferHistoryRepository transferHistoryRepository;

    public Boolean isTransferSuspicious(Account accountFrom, Account accountTo, BigDecimal moneyToTransfer){

        SuspiciousPerson suspiciousPersonFrom = null;
        SuspiciousPerson suspiciousPersonTo = null;

        try {
            suspiciousPersonFrom = suspiciousPersonRepository
                    .findSuspiciousPersonByFirstNameAndLastNameAndPesel(accountFrom.getCustomer().getFirstName(),
                            accountFrom.getCustomer().getLastName(), accountFrom.getCustomer().getPesel());
            suspiciousPersonTo = suspiciousPersonRepository
                    .findSuspiciousPersonByFirstNameAndLastNameAndPesel(accountTo.getCustomer().getFirstName(),
                            accountTo.getCustomer().getLastName(), accountTo.getCustomer().getPesel());
        } catch (NullPointerException e){}

        Boolean isPersonFromSuspicious;
        Boolean isPersonToSuspicious;

        //sprawdzanie czy jedna lub obie osoby sa podejrzane
        //jesli tak to juz mozna zwrocic true
        if(suspiciousPersonFrom != null){
            isPersonFromSuspicious = true;
        }
        if(suspiciousPersonTo != null){
            isPersonToSuspicious = true;
        }

        //czy wielkosc przelewu jest podejrzana





        return false;
    }

    //czy transakcja wykonywana nie jest wieksza niz ostatnie z 12 miesiecy
    private List<Optional<BigDecimal>> getBiggestTransactionsFromLast12Months(String firstName, String lastName) {
        return Stream
                .iterate(1, i -> i+1)
                .limit(12)
                .map(i -> i % 2 == 0 ? BigDecimal.valueOf(i*1000) : null)
                .map(Optional::ofNullable)
                .collect(Collectors.toList());
    }

    private BigDecimal getBiggestTransactionFromLast12Months(String bankAccountNumber){

//        List<TransferHistory> transferHistories = new LinkedList<>();
//        Iterable<TransferHistory> iterable = transferHistoryRepository.findAll();
//        iterable.forEach(e -> transferHistories.add(e));
//
//        BigDecimal biggestAmount = null;
//
//        for(TransferHistory history : transferHistories){
//            if(history.getBankAccountNumberFrom().equals(bankAccountNumber) || history.getBankAccountNumberTo().equals(bankAccountNumber)){
//                history.ge
//            }
//        }


        return null;
    }
}
