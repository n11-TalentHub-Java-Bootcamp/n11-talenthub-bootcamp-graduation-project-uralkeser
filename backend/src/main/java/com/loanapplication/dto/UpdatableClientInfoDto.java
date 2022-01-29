package com.loanapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatableClientInfoDto {
    private BigDecimal income;
    private BigDecimal deposit;
    private String mobileNumber;
}
