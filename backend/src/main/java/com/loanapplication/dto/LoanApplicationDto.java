package com.loanapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanApplicationDto {
    private Long clientId;
    private String status;
    private Date applicationDate;
    private BigDecimal loanAmount;
}
