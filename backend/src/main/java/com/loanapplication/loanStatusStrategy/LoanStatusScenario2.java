package com.loanapplication.loanStatusStrategy;

import com.loanapplication.converter.LoanApplicationConverter;
import com.loanapplication.dto.LoanApplicationDto;
import com.loanapplication.entity.Client;
import com.loanapplication.entity.LoanApplication;
import com.loanapplication.enums.LoanApplicationStatus;

import java.math.BigDecimal;

public class LoanStatusScenario2 implements LoanStatusScenario {
    public LoanApplication createLoanApplication(Client client, LoanApplicationDto loanApplicationDto){
        LoanApplication loanApplication = LoanApplicationConverter.INSTANCE.convertLoanApplicationDtoToLoanApplication(loanApplicationDto);
        BigDecimal loanAmount = new BigDecimal(10000);

        if( client.getDeposit().compareTo(new BigDecimal(0)) == 1 ){ //deposit > 0
            loanAmount = loanAmount.add(client.getDeposit().multiply(new BigDecimal(0.1))); // loanAmount = 10000 + 0.1 * deposit
        }

        loanApplication.setLoanAmount(loanAmount);
        loanApplication.setStatus(LoanApplicationStatus.APPROVED.getStatus());

        return loanApplication;
    }
}
