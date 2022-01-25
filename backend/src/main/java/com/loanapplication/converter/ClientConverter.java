package com.loanapplication.converter;

import com.loanapplication.dto.ClientDto;
import com.loanapplication.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientConverter {

    ClientConverter INSTANCE = Mappers.getMapper(ClientConverter.class);

    Client convertClientDtoToClient(ClientDto clientDto);

    ClientDto convertClientToClientDto(Client client);

    List<ClientDto> convertClientListToClientDtoList (List<Client> clientList);

}
