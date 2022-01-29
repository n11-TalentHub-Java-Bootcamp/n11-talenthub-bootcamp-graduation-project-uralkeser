package com.loanapplication.enums;

import java.math.BigDecimal;

public enum LoanLimitMultiplier {
    FULL(new BigDecimal(4)),
    HALF(new BigDecimal(2));

    private BigDecimal amount;

    LoanLimitMultiplier(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
