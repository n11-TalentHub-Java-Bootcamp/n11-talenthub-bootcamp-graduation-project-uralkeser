package com.loanapplication.controller;

import com.loanapplication.dto.ClientDto;
import com.loanapplication.dto.UpdatableClientInfoDto;
import com.loanapplication.entity.Client;
import com.loanapplication.entity.LoanApplication;
import com.loanapplication.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Tag(name = "Client Controller", description = "save update delete client - apply loan")
@CrossOrigin
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("")
    @Operation(summary = "save client and loan application")
    public ResponseEntity<ClientDto> saveNewClientAndLoanApplication(@RequestBody ClientDto clientDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveNewClientAndLoanApplication(clientDto));
    }

    @PutMapping("/{ssn}/{birthdate}")
    @Operation(summary = "update client and create new loan application")
    public ResponseEntity<ClientDto> updateClientAndCreateNewLoanApplication(@PathVariable("ssn") Long ssn, @PathVariable("birthdate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate, @RequestBody UpdatableClientInfoDto updatableClientInfoDto ){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.updateClientAndCreateNewLoanApplication(ssn, birthdate, updatableClientInfoDto));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete client and his/her all loan applications")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientService.deleteClientById(id));
    }

    @GetMapping("/loan-applications/{ssn}/{birthdate}")
    @Operation(summary = "show client's all loan applications")
    public ResponseEntity<Optional<List<LoanApplication>>> getClientsLoanApplicationBySsnAndBirthdate(@PathVariable("ssn") Long ssn, @PathVariable("birthdate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getClientsLoanApplicationBySsnAndBirthdate(ssn,birthdate));
    }



}
