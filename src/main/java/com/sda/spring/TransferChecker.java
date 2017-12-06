package com.sda.spring;

import com.sda.spring.entity.*;
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

    public Boolean isTransferSuspicious(Customer customerSender, Customer customerReciever, BigDecimal moneyToTransfer) {

        Boolean personIsSuspicious = isPersonSuspicious(customerSender, customerReciever);
        Boolean amountIsSuspicious = isAmountSuspicious(customerSender, moneyToTransfer);

        return personIsSuspicious || amountIsSuspicious;
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
                .orElse(BigDecimal.ZERO);

    }

    private Boolean isPersonSuspicious(Customer customerSender, Customer customerReciever) {

        SuspiciousPerson suspiciousPersonFrom = null;
        SuspiciousPerson suspiciousPersonTo = null;

        try {
            suspiciousPersonFrom = suspiciousPersonRepository
                    .findSuspiciousPersonByFirstNameAndLastNameAndPesel(customerSender.getFirstName(),
                            customerSender.getLastName(), customerSender.getPesel());
            suspiciousPersonTo = suspiciousPersonRepository
                    .findSuspiciousPersonByFirstNameAndLastNameAndPesel(customerReciever.getFirstName(),
                            customerReciever.getLastName(), customerReciever.getPesel());
        } catch (NullPointerException e) {
        }

        return suspiciousPersonFrom != null || suspiciousPersonTo != null;
    }

    private Boolean isAmountSuspicious(Customer customerSender, BigDecimal amountToCheck) {

        BigDecimal biggestTransactionOfAccountFromInLast12Months;
        Boolean amountSuspicious = null;

        try {
            biggestTransactionOfAccountFromInLast12Months = getBiggestTransactionFromLast12Months(
                    customerSender.getAccount().getBankAccountNumber());
            if (biggestTransactionOfAccountFromInLast12Months.doubleValue() * CHECKING_MULTIPLYER <= amountToCheck.doubleValue()) {
                amountSuspicious = true;
            } else {
                amountSuspicious = false;
            }
        } catch (NullPointerException e) {
        }
        return amountSuspicious;
    }
}
