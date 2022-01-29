package com.loanapplication.enums;

public enum ExceptionMessage {

    ClientHasAcceptedLoanApplicationException("Cannot update client having accepted loan application."),
    ClientNotFoundException("Cannot found client by given info."),
    LoanApplicationNotFoundException("Client does not have any loan application."),
    ClientAlreadyExistException("Client is already exist ");

    private String content;

    ExceptionMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
