package com.loanapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long ssn;
    private String fullName;
    private String mobileNumber;
    private LocalDate birthdate;
    private BigDecimal income;
    private BigDecimal deposit;


}
