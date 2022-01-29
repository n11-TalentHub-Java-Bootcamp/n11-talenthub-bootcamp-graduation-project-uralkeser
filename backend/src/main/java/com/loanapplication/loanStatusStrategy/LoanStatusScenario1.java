package com.loanapplication.loanStatusStrategy;


import com.loanapplication.converter.LoanApplicationConverter;
import com.loanapplication.dto.LoanApplicationDto;
import com.loanapplication.entity.Client;
import com.loanapplication.entity.LoanApplication;
import com.loanapplication.enums.LoanApplicationStatus;

import java.math.BigDecimal;
import java.util.Date;

public class LoanStatusScenario1 implements LoanStatusScenario {

    public LoanApplication createLoanApplication(Client client){
        return loanApplicationBuilder(client,new BigDecimal(0));
    }

    private LoanApplication loanApplicationBuilder(Client client,BigDecimal loanAmount ){

        LoanApplication loanApplication = LoanApplication
                .builder()
                .client(client)
                .loanAmount(loanAmount)
                .applicationDate(new Date())
                .status(LoanApplicationStatus.REJECTED.getStatus())
                .build();

        return loanApplication;
    }
}
