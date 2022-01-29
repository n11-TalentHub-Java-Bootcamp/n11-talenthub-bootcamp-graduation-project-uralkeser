package com.loanapplication.service;

import com.loanapplication.converter.ClientConverter;
import com.loanapplication.converter.LoanApplicationConverter;
import com.loanapplication.dto.ClientDto;
import com.loanapplication.dto.LoanApplicationDto;
import com.loanapplication.dto.UpdatableClientInfoDto;
import com.loanapplication.entity.Client;
import com.loanapplication.entity.LoanApplication;
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
import java.util.List;
import java.util.Optional;


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

        validationService.validateClient(optionalClient);

        clientRepository.deleteById(clientId);

        return "Deletion is successful";
    }

    public LoanApplicationDto saveTransaction(ClientDto clientDto){ // save client and loan application

        // check client if exist
        Optional<Client> optionalClient = clientRepository.getClientBySsn(clientDto.getSsn());
        validationService.validateNoneExistingClient(optionalClient);

        // getting client's credit score. it may be replaced with external service here
        int clientCreditScore = getClientCreditScore(clientDto.getIncome());

        // insert client into db
        Client client = saveNewClient(clientDto, clientCreditScore);

        // decide Loan Status Strategy
        decideLoanStatusStrategy(client);

        //create loanApplication according to loan status strategy
        LoanApplication loanApplication = executeLoanStatusStrategy(client);

        // insert loan application into db
        loanApplication = loanApplicationService.saveNewLoanApplication(loanApplication);

        // call sms sender
        sendSms(clientDto, loanApplication);

        // inform user with new loan application infos
        return LoanApplicationConverter.INSTANCE.convertLoanApplicationToLoanApplicationDto(loanApplication);
    }

    public LoanApplicationDto updateTransaction(Long ssn, LocalDate birthdate, UpdatableClientInfoDto updatableClientInfoDto){ //update client and save new loan application

        Client client  = getClientBySsnAndBirthdate(ssn,birthdate);

        // check whether client has any approved loan if s/he throw exception
        doesClientHaveAnyApprovedLoan(client.getId());

        // update client
        client =  updateClient(client, updatableClientInfoDto);

        // decide Loan Status Strategy
        decideLoanStatusStrategy(client);

        // create loanApplication according to loan status strategy
        LoanApplication loanApplication = executeLoanStatusStrategy(client);

        // insert loan application into db
        loanApplicationService.saveNewLoanApplication(loanApplication);


        ClientDto clientDto = ClientConverter.INSTANCE.convertClientToClientDto(client);

        // call sms sender
        sendSms(clientDto, loanApplication);

        // inform user with new loan application infos
        return LoanApplicationConverter.INSTANCE.convertLoanApplicationToLoanApplicationDto(loanApplication);
    }

    private Client getClientBySsnAndBirthdate(Long ssn, LocalDate birthdate){

        Optional<Client> optionalClient = clientRepository.getClientBySsnAndBirthdate(ssn,birthdate);

        Client client = validationService.validateClient(optionalClient);

        return client;
    }

    public List<LoanApplicationDto> getClientsLoanApplicationBySsnAndBirthdate(Long ssn, LocalDate birthdate){
        log.warn("call get all loan applications service by ssn:" + ssn + " and " + birthdate);
        Client client = getClientBySsnAndBirthdate(ssn,birthdate);
        Optional<List<LoanApplication>> optionalLoanApplicationList = loanApplicationService.getAllLoanApplicationsByClientId(client.getId());
        List<LoanApplication> loanApplicationList = validationService.validateLoanApplicationList(optionalLoanApplicationList);
        return LoanApplicationConverter.INSTANCE.convertLoanApplicationListToLoanApplicationDtoList(loanApplicationList);
    }

    private void doesClientHaveAnyApprovedLoan(Long clientId){
        Optional<List<LoanApplication>> optionalLoanApplicationList = loanApplicationService.getAllLoanApplicationsByClientId(clientId);

        List<LoanApplication> loanApplicationList = validationService.validateLoanApplicationList(optionalLoanApplicationList);

        validationService.validateApprovedLoan(loanApplicationList);
    }

    private Client saveNewClient(ClientDto clientDto, int clientCreditScore){
        Client client = ClientConverter.INSTANCE.convertClientDtoToClient(clientDto);
        client.setCreditScore(clientCreditScore);
        client = clientRepository.save(client);

        log.warn(client.getFullName()+" inserted into database");

        return client;
    }

    private Client updateClient(Client client, UpdatableClientInfoDto updatableClientInfoDto){
        client.setIncome(updatableClientInfoDto.getIncome());
        client.setDeposit(updatableClientInfoDto.getDeposit());
        client.setCreditScore(getClientCreditScore(updatableClientInfoDto.getIncome()));
        client.setMobileNumber(updatableClientInfoDto.getMobileNumber());

        log.warn(client.getFullName()+" updated in database.");

        return client;
    }

    private int getClientCreditScore(BigDecimal income){ //may be replaced with external service
        if(income.compareTo(new BigDecimal(4500)) < 0){
            log.warn("retrieved credit score:0");
            return 0;
        }
        else if(income.compareTo(new BigDecimal(6000)) <= 0){
            log.warn("retrieved credit score:500");
            return 500;
        }
        else if(income.compareTo(new BigDecimal(10000)) <= 0){
            log.warn("retrieved credit score:600");
            return 600;
        }
        else if(income.compareTo(new BigDecimal(15000)) <= 0){
            log.warn("retrieved credit score:750");
            return 750;
        }
        else if(income.compareTo(new BigDecimal(20000)) <= 0){
            log.warn("retrieved credit score:850");
            return 850;
        }
        log.warn("retrieved credit score:1000");
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

    private LoanApplication executeLoanStatusStrategy(Client client){ // returns loanApplication according to loan status strategy
        return loanStatusScenario.createLoanApplication(client);
    }

    private void sendSms(ClientDto clientDto,LoanApplication loanApplication){ //may be replaced with external service
        log.info("\nSayın "+ clientDto.getFullName() + " kredi başvuru sonuç bilgileriniz:\n" + "Onay durumu: " + loanApplication.getStatus() + "\nLimit: " + loanApplication.getLoanAmount()+"\nşeklinde oluşturulmuştur.");
    }

}
