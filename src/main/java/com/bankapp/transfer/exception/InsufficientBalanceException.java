package com.bankapp.transfer.exception;

public class InsufficientBalanceException  extends RuntimeException{ //unchecked exception 

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
