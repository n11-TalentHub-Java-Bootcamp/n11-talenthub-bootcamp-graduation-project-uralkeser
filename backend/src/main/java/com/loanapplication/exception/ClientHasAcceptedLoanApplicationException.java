package com.loanapplication.exception;

public class ClientHasAcceptedLoanApplicationException extends RuntimeException{
    public ClientHasAcceptedLoanApplicationException(String message){
        super(message);
    }
}