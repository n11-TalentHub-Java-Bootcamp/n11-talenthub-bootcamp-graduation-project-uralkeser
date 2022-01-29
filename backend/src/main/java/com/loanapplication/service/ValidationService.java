package com.loanapplication.service;

import com.loanapplication.entity.Client;
import com.loanapplication.entity.LoanApplication;
import com.loanapplication.enums.ExceptionMessage;
import com.loanapplication.enums.LoanApplicationStatus;
import com.loanapplication.exception.ClientAlreadyExistException;
import com.loanapplication.exception.ClientHasAcceptedLoanApplicationException;
import com.loanapplication.exception.ClientNotFoundException;
import com.loanapplication.exception.LoanApplicationNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class ValidationService {

    public Client validateClient(Optional<Client> client){
        if(client.isPresent()){
            return client.get();
        }
        else {
          throw new ClientNotFoundException(ExceptionMessage.ClientNotFoundException.getContent());
        }
    }

    public void validateNoneExistingClient(Optional<Client> client){
        if(client.isPresent()){
            throw new ClientAlreadyExistException(ExceptionMessage.ClientAlreadyExistException.getContent());
        }
    }

    public List<LoanApplication> validateLoanApplicationList(Optional<List<LoanApplication>> LoanApplicationList){
        if(LoanApplicationList.isPresent()){
            return LoanApplicationList.get();
        }
        else {
            throw new LoanApplicationNotFoundException(ExceptionMessage.LoanApplicationNotFoundException.getContent());
        }
    }

    public void validateApprovedLoan(List<LoanApplication> loanApplicationList){
        loanApplicationList =  loanApplicationList.stream()
                .filter( loanApplication -> loanApplication.getStatus().equals(LoanApplicationStatus.APPROVED.getStatus()))   // filtering approved
                .collect(Collectors.toList());

        if(!loanApplicationList.isEmpty()){
            log.info("the client has approved loan");
            throw new ClientHasAcceptedLoanApplicationException(ExceptionMessage.ClientHasAcceptedLoanApplicationException.getContent());
        }
        log.info("the client does not have approved loan");
    }

}
