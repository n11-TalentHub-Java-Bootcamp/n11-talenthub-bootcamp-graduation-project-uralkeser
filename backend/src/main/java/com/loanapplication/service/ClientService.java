package com.loanapplication.service;

import com.loanapplication.converter.ClientConverter;
import com.loanapplication.dto.ClientDto;
import com.loanapplication.entity.Client;
import com.loanapplication.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoanApplicationService loanApplicationService;

    public void deleteClientById(Long clientId){
        //check id and throw exception //TODO

        clientRepository.deleteById(clientId);
    }

    public void saveNewClientAndCreateLoanApplication(ClientDto clientDto){

        //clientDto formatı doğru geldi mi? bad.request exception

        if(isClientExist(clientDto.getSsn())) {
            // return client has already applied for loan exception
        }

        // getting client's credit score may be replaced with external service here
        int clientCreditScore = getClientCreditScore(clientDto.getIncome());

        // insert client into db
        saveNewClient(clientDto, clientCreditScore);

        //insert loan application into db
        //loanApplicationService.saveLoanApplication()//TODO

        //send SMS//TODO
        //sendSMS
    }

    public void updateClientAndCreateNewLoanApplication(ClientDto clientDto){

        //clientDto formatı doğru geldi mi? bad.request exception

        List<Client> clientList = getClientBySsnAndBirthdate(clientDto.getSsn(),clientDto.getBirthdate());

        if(doesClientHaveApprovedLoan()){//TODO
            //return client has approved loan exception
        }

        Client client = clientList.get(0);

        //update client
        client =  updateClient(client, clientDto);

        //update loan application into db
        //loanApplicationService.updateLoanApplication()//TODO

    }

    public List<Client> getClientBySsnAndBirthdate(Long ssn, LocalDate birthdate){

        List<Client> clientList = clientRepository.getClientBySsnAndBirthdate(ssn,birthdate);

        if(clientList.isEmpty()){//TODO
            //return mismatched info exception
        }

        return clientList;
    }

    private List<Client> getClientBySsn(Long ssn){
        List<Client> clientList = clientRepository.getClientBySsn(ssn);

        return clientList;
    }

    private boolean isClientExist(Long ssn){
        List<Client> clientList = getClientBySsn(ssn);

        if(clientList.isEmpty())
            return false;
        else
            return true;
    }

    private boolean doesClientHaveApprovedLoan(){
        // List<LoanApplication> listLoanApplication = getAllLoanApplicationByClientId();

//        for (LoanApplication loanApplication : listLoanApplication) {
//            if(loanApplication.getCreditStatus().equals("ONAY")){
//                return true;
//            }
//        }

        return false;
    }

    private Client saveNewClient(ClientDto clientDto, int clientCreditScore){
        Client client = ClientConverter.INSTANCE.convertClientDtoToClient(clientDto);
        client.setCreditScore(clientCreditScore);

        return clientRepository.save(client);
    }

    private Client updateClient(Client client, ClientDto clientDto){
        client.setIncome(clientDto.getIncome());
        client.setDeposit(clientDto.getDeposit());
        client.setCreditScore(getClientCreditScore(client.getIncome()));
        client.setMobileNumber(clientDto.getMobileNumber());

        return clientRepository.save(client);
    }

    private int getClientCreditScore(BigDecimal income){
        if(income.compareTo(new BigDecimal(4500)) == 0){
            return 0;
        }
        else if(income.compareTo(new BigDecimal(6000)) == 0){
            return 200;
        }
        else if(income.compareTo(new BigDecimal(10000)) == 0){
            return 500;
        }
        else if(income.compareTo(new BigDecimal(15000)) == 0){
            return 750;
        }
        else if(income.compareTo(new BigDecimal(20000)) == 0){
            return 850;
        }
        return 1000;
    }

    private void sendSms(){ //TODO

    }

}
