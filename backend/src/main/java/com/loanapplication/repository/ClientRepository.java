package com.loanapplication.repository;

import com.loanapplication.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    List<Client> getClientBySsn(Long ssn);

    List<Client> getClientBySsnAndBirthdate(Long ssn, LocalDate birthdate);

}
