package com.loanapplication.loanStatusStrategy;


import com.loanapplication.converter.LoanApplicationConverter;
import com.loanapplication.dto.LoanApplicationDto;
import com.loanapplication.entity.Client;
import com.loanapplication.entity.LoanApplication;
import com.loanapplication.enums.LoanApplicationStatus;

import java.math.BigDecimal;

public class LoanStatusScenario1 implements LoanStatusScenario {
    public LoanApplication createLoanApplication(Client client, LoanApplicationDto loanApplicationDto){
        LoanApplication loanApplication = LoanApplicationConverter.INSTANCE.convertLoanApplicationDtoToLoanApplication(loanApplicationDto);

        loanApplication.setLoanAmount(new BigDecimal(0));
        loanApplication.setStatus(LoanApplicationStatus.REJECTED.getStatus());

        return loanApplication;
    }
}
