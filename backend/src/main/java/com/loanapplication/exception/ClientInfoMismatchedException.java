package com.loanapplication.exception;

public class ClientInfoMismatchedException extends RuntimeException{
    public ClientInfoMismatchedException(String message){
        super(message);
    }
}