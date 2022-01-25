package com.loanapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long ssn;
    private String fullName;
    private String mobileNumber;
    private Date birthdate;
    private BigDecimal income;
    private BigDecimal deposit;


}
