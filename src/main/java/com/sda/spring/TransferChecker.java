package com.sda.spring;

import com.sda.spring.entity.Account;
import com.sda.spring.entity.SuspiciousPerson;
import com.sda.spring.entity.SuspiciousTransferHistory;
import com.sda.spring.entity.TransferHistory;
import com.sda.spring.repository.SuspiciousPersonRepository;
import com.sda.spring.service.TransferHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class TransferChecker {

    private SuspiciousPersonRepository suspiciousPersonRepository;
    private TransferHistoryService transferHistoryService;

    @Autowired
    public TransferChecker(SuspiciousPersonRepository suspiciousPersonRepository, TransferHistoryService transferHistoryService) {
        this.suspiciousPersonRepository = suspiciousPersonRepository;
        this.transferHistoryService = transferHistoryService;
    }

    private final static Integer CHECKING_MULTIPLYER = 1000;

    public Boolean isTransferSuspicious(Account accountFrom, Account accountTo, BigDecimal moneyToTransfer) {

//        SuspiciousPerson suspiciousPersonFrom = null;
//        SuspiciousPerson suspiciousPersonTo = null;
        Boolean personIsSuspicious = isPersonSuspicious(accountFrom, accountTo);
        Boolean amountIsSuspicious = null;

//        try {
//            suspiciousPersonFrom = suspiciousPersonRepository
//                    .findSuspiciousPersonByFirstNameAndLastNameAndPesel(accountFrom.getCustomer().getFirstName(),
//                            accountFrom.getCustomer().getLastName(), accountFrom.getCustomer().getPesel());
//            suspiciousPersonTo = suspiciousPersonRepository
//                    .findSuspiciousPersonByFirstNameAndLastNameAndPesel(accountTo.getCustomer().getFirstName(),
//                            accountTo.getCustomer().getLastName(), accountTo.getCustomer().getPesel());
//        } catch (NullPointerException e) {
//        }


        SuspiciousTransferHistory suspiciousTransferHistory = null;

        //sprawdzanie czy jedna lub obie osoby sa podejrzane
        //jesli tak to juz mozna zwrocic true
        if (personIsSuspicious) {
//            isPersonSuspicious = true;
            suspiciousTransferHistory = new SuspiciousTransferHistory();
            suspiciousTransferHistory.setBankAccountNumberFrom(accountFrom.getBankAccountNumber());
            suspiciousTransferHistory.setBankAccountNumberTo(accountTo.getBankAccountNumber());
            suspiciousTransferHistory.setDate(LocalDateTime.now());
            suspiciousTransferHistory.setAmount(moneyToTransfer);
            suspiciousTransferHistory.setPersonFromBlackList(true);
        }

        //czy wielkosc przelewu jest podejrzana
        //wywolac metode ktora zwroci najwieksza transakcje z ostatnich 12 miesiecy
        //jesli kwota danego przelewu jest wieksza o X (stała w kodzie) od najwiekszej z ost 12 miesciecy -> podejrzane

//        BigDecimal biggestTransactionOfAccountFromInLast12Months;
//
//        try {
//            biggestTransactionOfAccountFromInLast12Months = getBiggestTransactionFromLast12Months(
//                    accountFrom.getBankAccountNumber());
//            if(biggestTransactionOfAccountFromInLast12Months.doubleValue() * CHECKING_MULTIPLYER < moneyToTransfer.doubleValue()){
//                suspiciousTransferHistory.setAmountSuspicious(true);
//            } else {
//                suspiciousTransferHistory.setAmountSuspicious(false);
//            }
//        } catch (NullPointerException e){}

        if(personIsSuspicious || amountIsSuspicious){
            return true;
        }
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

    //publiczne do testow
    public BigDecimal getBiggestTransferFromLast12Moths(String bankAccountNumber) {
        return getBiggestTransactionFromLast12Months(bankAccountNumber);
    }


    private BigDecimal getBiggestTransactionFromLast12Months(String bankAccountNumber) {

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime limitDate = LocalDateTime.of(currentDate.getYear() - 1, currentDate.getMonth(),
                currentDate.getDayOfMonth(), currentDate.getHour(), currentDate.getMinute(), currentDate.getSecond(),
                currentDate.getNano());

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

    private Boolean isPersonSuspicious(Account accountFrom, Account accountTo){

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

        if(suspiciousPersonFrom != null || suspiciousPersonTo != null){
            return true;
        } else
        return false;
    }

    private Boolean isAmountSuspicious(Account accountFrom, BigDecimal amountToCheck){

        BigDecimal biggestTransactionOfAccountFromInLast12Months;

        try {
            biggestTransactionOfAccountFromInLast12Months = getBiggestTransactionFromLast12Months(
                    accountFrom.getBankAccountNumber());
            if(biggestTransactionOfAccountFromInLast12Months.doubleValue() * CHECKING_MULTIPLYER < amountToCheck.doubleValue()){
//                suspiciousTransferHistory.setAmountSuspicious(true);
            } else {
//                suspiciousTransferHistory.setAmountSuspicious(false);
            }
        } catch (NullPointerException e){}


        return true;
    }
}
