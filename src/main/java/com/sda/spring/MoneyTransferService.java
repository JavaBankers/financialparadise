package com.sda.spring;

import com.sda.spring.entity.Account;
import com.sda.spring.entity.TransferHistory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MoneyTransferService {

    private final static BigDecimal BALANCE_LIMIT = BigDecimal.ZERO;

    private TransferHistory transferHistory;

    private static LocalDateTime getCurrentDateAndTime(){
        return LocalDateTime.now();
    }

    public void transferMoney(Account accountFrom, Account accountTo, BigDecimal moneyToTransfer) throws InsufficientFundsException{

        if(moneyToTransfer.compareTo(accountFrom.getBalance()) < 1 && accountFrom.getBalance().compareTo(BALANCE_LIMIT) >= 0){
            accountFrom.setBalance(accountFrom.getBalance().subtract(moneyToTransfer));
            accountTo.setBalance(accountTo.getBalance().add(moneyToTransfer));
            this.transferHistory = new TransferHistory(
                    accountFrom.getBankAccountNumber(),
                    accountTo.getBankAccountNumber(),
                    getCurrentDateAndTime(),
                    moneyToTransfer);
        } else {
            throw new InsufficientFundsException();
        }
    }
}

