package com.loanapplication.loanStatusStrategy;

import com.loanapplication.dto.LoanApplicationDto;
import com.loanapplication.entity.Client;
import com.loanapplication.entity.LoanApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface LoanStatusScenario {
    public LoanApplication createLoanApplication(Client client);

    private LoanApplication loanApplicationBuilder(Client client, BigDecimal loanAmount){return new LoanApplication();};

}
