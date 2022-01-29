package com.loanapplication.service;

import com.loanapplication.entity.LoanApplication;
import com.loanapplication.repository.LoanApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class LoanApplicationService {

    @Autowired
    LoanApplicationRepository loanApplicationRepository;

    public LoanApplication saveNewLoanApplication(LoanApplication loanApplication){
        return loanApplicationRepository.save(loanApplication);
    }

    public Optional<List<LoanApplication>> getAllLoanApplicationsByClientId(Long clientId){
        return loanApplicationRepository.getLoanApplicationByClient_Id(clientId);
    }




}
