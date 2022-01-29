package com.loanapplication.converter;

import com.loanapplication.dto.LoanApplicationDto;
import com.loanapplication.entity.LoanApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanApplicationConverter {

    LoanApplicationConverter INSTANCE = Mappers.getMapper(LoanApplicationConverter.class);

    @Mapping(target="client.id", source = "clientId")
    LoanApplication convertLoanApplicationDtoToLoanApplication(LoanApplicationDto loanApplicationDto);

    @Mapping(target="clientId", source = "client.id")
    LoanApplicationDto convertLoanApplicationToLoanApplicationDto(LoanApplication loanApplication);

    @Mapping(target="clientId", source = "client.id")
    List<LoanApplicationDto> convertLoanApplicationListToLoanApplicationDtoList(List<LoanApplication> loanApplicationList);

}
