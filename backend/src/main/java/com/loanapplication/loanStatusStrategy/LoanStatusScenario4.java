package com.loanapplication.loanStatusStrategy;

import com.loanapplication.converter.LoanApplicationConverter;
import com.loanapplication.dto.LoanApplicationDto;
import com.loanapplication.entity.Client;
import com.loanapplication.entity.LoanApplication;
import com.loanapplication.enums.LoanApplicationStatus;
import com.loanapplication.enums.LoanLimitMultiplier;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Slf4j
public class LoanStatusScenario4 implements LoanStatusScenario {
    public LoanApplication createLoanApplication(Client client){
        log.warn("applied loan application scenario is: 4");
        BigDecimal loanAmount = client.getIncome().multiply(LoanLimitMultiplier.HALF.getAmount());

        if( client.getDeposit().compareTo(new BigDecimal(0)) == 1 ){ //deposit > 0
            loanAmount = loanAmount.add(client.getDeposit().multiply(new BigDecimal(0.25))).setScale(2, RoundingMode.HALF_UP); // loanAmount = income * 2 + 0.25 * deposit
        }

        return loanApplicationBuilder(client, loanAmount);
    }

    private LoanApplication loanApplicationBuilder(Client client,BigDecimal loanAmount ){

        LoanApplication loanApplication = LoanApplication
                .builder()
                .client(client)
                .loanAmount(loanAmount)
                .applicationDate(new Date())
                .status(LoanApplicationStatus.APPROVED.getStatus())
                .build();

        return loanApplication;
    }
}