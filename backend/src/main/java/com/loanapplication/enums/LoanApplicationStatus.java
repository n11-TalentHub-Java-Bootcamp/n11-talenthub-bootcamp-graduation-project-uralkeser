package com.loanapplication.enums;

public enum LoanApplicationStatus {
    APPROVED("onay"),
    REJECTED("red");

    private String status;

    LoanApplicationStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}
