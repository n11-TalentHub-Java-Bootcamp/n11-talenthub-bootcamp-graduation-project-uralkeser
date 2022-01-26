package com.loanapplication.repository;

import com.loanapplication.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication,Long> {

    List<LoanApplication> getLoanApplicationByClient_Id(Long clientId);


}
