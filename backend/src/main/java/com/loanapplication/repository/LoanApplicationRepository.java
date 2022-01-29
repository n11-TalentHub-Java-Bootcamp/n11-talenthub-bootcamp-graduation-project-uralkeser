package com.loanapplication.repository;

import com.loanapplication.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication,Long> {

    Optional<List<LoanApplication>> getLoanApplicationByClient_Id(Long clientId);



}
