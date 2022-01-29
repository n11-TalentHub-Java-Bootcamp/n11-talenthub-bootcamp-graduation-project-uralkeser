package com.loanapplication.service;

import com.loanapplication.entity.Client;
import com.loanapplication.entity.LoanApplication;
import com.loanapplication.enums.ExceptionMessage;
import com.loanapplication.exception.ClientNotFoundException;
import com.loanapplication.exception.LoanApplicationNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ValidationService {

    public Client validateClient(Optional<Client> client){
        if(client.isPresent()){
            return client.get();
        }
        else {
          throw new ClientNotFoundException(ExceptionMessage.ClientNotFoundException.getContent());
        }
    }

    public Boolean isClientExist(Optional<Client> client){
        if(client.isPresent()){
            return true;
        }
        else {
            return false;
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

}
