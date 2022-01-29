package com.loanapplication.enums;

public enum ExceptionMessage {

    ClientHasAcceptedLoanApplicationException("Cannot update client having accepted loan application."),
    ClientInfoMismatchedException("Given birthdate and ssn do not belong to a client."),
    ClientNotFoundException("Cannot found client by given info."),
    LoanApplicationNotFoundException("Client does not have any loan application.");
    private String content;

    ExceptionMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
