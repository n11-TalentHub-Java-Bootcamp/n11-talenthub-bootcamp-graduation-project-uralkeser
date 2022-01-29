package com.loanapplication.repository;

import com.loanapplication.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> getClientById(Long id);

    Optional<Client> getClientBySsn(Long ssn);

    Optional<Client> getClientBySsnAndBirthdate(Long ssn, LocalDate birthdate);

}
