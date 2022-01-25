package com.loanapplication.service;

import com.loanapplication.entity.Client;
import com.loanapplication.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

    public void deleteClientById(Long clientId){
        clientRepository.deleteById(clientId);
    }

    private Client getClientBySsn(Long ssn){
        List<Client> clientList = clientRepository.getClientBySsn(ssn);

        if(clientList.isEmpty()){
            return null;
        }

        return clientList.get(0);
    }

    private boolean isClientExist(Long ssn){
        if(getClientBySsn(ssn) == null)
            return false;
        else
            return true;
    }

    private int getCreditScore(BigDecimal income){ //TODO
        return 1000;
    }

    private void sendSms(){ //TODO

    }

}
