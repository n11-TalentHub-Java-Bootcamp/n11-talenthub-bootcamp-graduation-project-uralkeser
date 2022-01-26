package com.loanapplication.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ssn", nullable = false, length = 11, unique = true, updatable = false)
    private Long ssn;

    @Column(name = "full_name", nullable = false, length = 50, updatable = false)
    private String fullName;

    @Column(name = "mobile_number", nullable = false, length = 15, unique = true)
    private String mobileNumber;

    @Column(name = "birthdate",nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @Column(name = "income",nullable = false, precision = 19, scale = 2)
    private BigDecimal income;

    @Column(name = "deposit", precision = 19, scale = 2)
    private BigDecimal deposit;

    @Column(name = "credit_score")
    private int creditScore;

}
