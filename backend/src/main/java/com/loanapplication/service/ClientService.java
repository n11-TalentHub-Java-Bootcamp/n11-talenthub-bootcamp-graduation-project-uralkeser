package com.loanapplication.service;

import com.loanapplication.converter.ClientConverter;
import com.loanapplication.dto.ClientDto;
import com.loanapplication.dto.LoanApplicationDto;
import com.loanapplication.dto.UpdatableClientInfoDto;
import com.loanapplication.entity.Client;
import com.loanapplication.entity.LoanApplication;
import com.loanapplication.enums.ExceptionMessage;
import com.loanapplication.enums.LoanApplicationStatus;
import com.loanapplication.exception.ClientHasAcceptedLoanApplicationException;
import com.loanapplication.exception.ClientNotFoundException;
import com.loanapplication.loanStatusStrategy.LoanStatusScenario1;
import com.loanapplication.loanStatusStrategy.LoanStatusScenario2;
import com.loanapplication.loanStatusStrategy.LoanStatusScenario3;
import com.loanapplication.loanStatusStrategy.LoanStatusScenario4;
import com.loanapplication.loanStatusStrategy.LoanStatusScenario5;

import com.loanapplication.loanStatusStrategy.LoanStatusScenario;
import com.loanapplication.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LoanApplicationService loanApplicationService;

    @Autowired
    private ValidationService validationService;

    private LoanStatusScenario loanStatusScenario;

    public String deleteClientById(Long clientId){
        Optional<Client> optionalClient = clientRepository.getClientById(clientId);

        if(validationService.isClientExist(optionalClient)) {//TODO
            throw new ClientNotFoundException(ExceptionMessage.ClientNotFoundException.getContent());
        }

        clientRepository.deleteById(clientId);

        return "Deletion is successful";
    }

    public ClientDto saveNewClientAndLoanApplication(ClientDto clientDto){


        Optional<Client> optionalClient = clientRepository.getClientBySsn(clientDto.getSsn());

        if(validationService.isClientExist(optionalClient)) {//TODO
            throw  new ClientHasAcceptedLoanApplicationException(ExceptionMessage.ClientHasAcceptedLoanApplicationException.getContent());
        }

        // getting client's credit score. it may be replaced with external service here
        int clientCreditScore = getClientCreditScore(clientDto.getIncome());

        // insert client into db
        Client client = saveNewClient(clientDto, clientCreditScore);

        // decide Loan Status Strategy
        decideLoanStatusStrategy(client);

        // build LoanApplicationDto with clientId and today's date
        LoanApplicationDto loanApplicationDto = LoanApplicationDto
                .builder()
                .clientId(client.getId())
                .applicationDate(new Date())
                .build();

        //create loanApplication according to loan status strategy
        LoanApplication loanApplication = executeLoanStatusStrategy(client, loanApplicationDto);

        // insert loan application into db
        loanApplicationService.saveNewLoanApplication(loanApplication);

        sendSms(clientDto, loanApplication);

        return clientDto;
    }

    public ClientDto updateClientAndCreateNewLoanApplication(Long ssn, LocalDate birthdate, UpdatableClientInfoDto updatableClientInfoDto){

        //updatableClientInfoDto formatı doğru geldi mi? bad.request exception

        Client client  = getClientBySsnAndBirthdate(ssn,birthdate);

        if(doesClientHaveAnyApprovedLoan(client.getId())){
            throw new ClientHasAcceptedLoanApplicationException(ExceptionMessage.ClientHasAcceptedLoanApplicationException.getContent());
        }

        //update client
        client =  updateClient(client, updatableClientInfoDto);

        // decide Loan Status Strategy
        decideLoanStatusStrategy(client);

        // build LoanApplicationDto with clientId and today's date
        LoanApplicationDto loanApplicationDto = LoanApplicationDto
                .builder()
                .clientId(client.getId())
                .applicationDate(new Date())
                .build();

        //create loanApplication according to loan status strategy
        LoanApplication loanApplication = executeLoanStatusStrategy(client, loanApplicationDto);

        // insert loan application into db
        loanApplicationService.saveNewLoanApplication(loanApplication);

        //send SMS//TODO

        ClientDto clientDto = ClientConverter.INSTANCE.convertClientToClientDto(client);

        return clientDto;

    }

    private Client getClientBySsnAndBirthdate(Long ssn, LocalDate birthdate){

        Optional<Client> optionalClient = clientRepository.getClientBySsnAndBirthdate(ssn,birthdate);

        Client client = validationService.validateClient(optionalClient);

        return client;
    }

    public Optional<List<LoanApplication>> getClientsLoanApplicationBySsnAndBirthdate(Long ssn, LocalDate birthdate){
        Client client = getClientBySsnAndBirthdate(ssn,birthdate);
        return loanApplicationService.getAllLoanApplicationsByClientId(client.getId());
    }

    private boolean doesClientHaveAnyApprovedLoan(Long clientId){
        Optional<List<LoanApplication>> optionalLoanApplicationList = loanApplicationService.getAllLoanApplicationsByClientId(clientId);

        List<LoanApplication> loanApplicationList = validationService.validateLoanApplicationList(optionalLoanApplicationList);

        loanApplicationList =  loanApplicationList.stream()
                .filter( loanApplication -> loanApplication.getStatus().equals(LoanApplicationStatus.APPROVED.getStatus()))   // filtering approved
                .collect(Collectors.toList());

        if(loanApplicationList.isEmpty()){
            return false;
        }

        return true;
    }

    private Client saveNewClient(ClientDto clientDto, int clientCreditScore){
        Client client = ClientConverter.INSTANCE.convertClientDtoToClient(clientDto);
        client.setCreditScore(clientCreditScore);

        return clientRepository.save(client);
    }

    private Client updateClient(Client client, UpdatableClientInfoDto updatableClientInfoDto){
        client.setIncome(updatableClientInfoDto.getIncome());
        client.setDeposit(updatableClientInfoDto.getDeposit());
        client.setCreditScore(getClientCreditScore(updatableClientInfoDto.getIncome()));
        client.setMobileNumber(updatableClientInfoDto.getMobileNumber());

        return clientRepository.save(client);
    }

    private int getClientCreditScore(BigDecimal income){
        if(income.compareTo(new BigDecimal(4500)) < 0){
            return 0;
        }
        else if(income.compareTo(new BigDecimal(6000)) <= 0){
            return 500;
        }
        else if(income.compareTo(new BigDecimal(10000)) <= 0){
            return 600;
        }
        else if(income.compareTo(new BigDecimal(15000)) <= 0){
            return 750;
        }
        else if(income.compareTo(new BigDecimal(20000)) <= 0){
            return 850;
        }
        return 1000;
    }

    private void decideLoanStatusStrategy(Client client){

        if( client.getCreditScore() < 500 ){
            setLoanStatusStrategy(new LoanStatusScenario1());
        }
        else if( client.getCreditScore() < 1000 ){ // 500 < creditScore < 1000
            if( client.getIncome().compareTo(new BigDecimal(5000)) < 0){ //  income < 5000
                setLoanStatusStrategy(new LoanStatusScenario2());
            }
            else if( client.getIncome().compareTo(new BigDecimal(10000)) < 0) { // 5000 < income < 10000
                setLoanStatusStrategy(new LoanStatusScenario3());
            }
            else { // income >= 10000
                setLoanStatusStrategy(new LoanStatusScenario4());
            }
        }
        else { //  creditScore >= 1000
            setLoanStatusStrategy(new LoanStatusScenario5());
        }

    }

    private void setLoanStatusStrategy(LoanStatusScenario loanStatusScenario) {
        this.loanStatusScenario = loanStatusScenario;
    }

    private LoanApplication executeLoanStatusStrategy(Client client, LoanApplicationDto loanApplicationDto){ // returns loanApplication according to loan status strategy
        return loanStatusScenario.createLoanApplication(client, loanApplicationDto);
    }

    private void sendSms(ClientDto clientDto,LoanApplication loanApplication){ //TODO
        log.info(clientDto.getMobileNumber()+" numaraya"+  loanApplication.getLoanAmount()+ "limitli" + "kredi sonucu"  +loanApplication.getStatus()+" olarak" +"gönderildi.");
    }

}
