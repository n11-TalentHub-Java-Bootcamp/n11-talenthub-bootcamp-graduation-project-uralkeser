package com.loanapplication.controller;

import com.loanapplication.dto.ClientDto;
import com.loanapplication.entity.Client;
import com.loanapplication.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/saveNewClientAndCreateLoanApplication")
    public void saveNewClientAndCreateLoanApplication(@RequestBody ClientDto clientDto){

    }

    @PostMapping("/updateClientAndCreateNewLoanApplication")
    public void updateClientAndCreateNewLoanApplication(@RequestBody ClientDto clientDto){

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        clientService.deleteClientById(id);
    }

    @GetMapping("/ssn/{ssn}/birthdate/{birthdate}")
    public List<Client> getClientBySsnAndBirthdate(@PathVariable("ssn") Long ssn, @PathVariable("birthdate") LocalDate birthdate){
        return clientService.getClientBySsnAndBirthdate(ssn,birthdate);
    }




}
