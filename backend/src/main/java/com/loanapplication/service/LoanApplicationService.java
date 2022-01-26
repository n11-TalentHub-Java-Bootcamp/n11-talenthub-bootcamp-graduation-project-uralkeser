package com.loanapplication.service;

import com.loanapplication.repository.LoanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanApplicationService {

    @Autowired
    LoanApplicationRepository loanApplicationRepository;


}
