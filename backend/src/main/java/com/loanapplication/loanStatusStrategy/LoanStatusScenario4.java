package com.loanapplication.loanStatusStrategy;

import com.loanapplication.converter.LoanApplicationConverter;
import com.loanapplication.dto.LoanApplicationDto;
import com.loanapplication.entity.Client;
import com.loanapplication.entity.LoanApplication;
import com.loanapplication.enums.LoanApplicationStatus;
import com.loanapplication.enums.LoanLimitMultiplier;

import java.math.BigDecimal;

public class LoanStatusScenario4 implements LoanStatusScenario {
    public LoanApplication createLoanApplication(Client client, LoanApplicationDto loanApplicationDto){
        LoanApplication loanApplication = LoanApplicationConverter.INSTANCE.convertLoanApplicationDtoToLoanApplication(loanApplicationDto);
        BigDecimal loanAmount = client.getIncome().multiply(LoanLimitMultiplier.HALF.getAmount());

        if( client.getDeposit().compareTo(new BigDecimal(0)) == 1 ){ //deposit > 0
            loanAmount = loanAmount.add(client.getDeposit().multiply(new BigDecimal(0.25))); // loanAmount = income * 2 + 0.25 * deposit
        }

        loanApplication.setLoanAmount(loanAmount);
        loanApplication.setStatus(LoanApplicationStatus.APPROVED.getStatus());

        return loanApplication;
    }
}