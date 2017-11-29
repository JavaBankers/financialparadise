package com.sda.spring;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(){
        super("SORRY, INSUFFICIENT FUNDS");
    }
}
