package com.ntt.clients.mapper;

import com.ntt.clients.dto.ClientDTO;
import com.ntt.clients.dto.ManageClientDTO;
import com.ntt.clients.models.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client toEntity(ManageClientDTO dto) {
        return Client.builder()
                .name(dto.name())
                .gender(dto.gender())
                .age(dto.age())
                .identifier(dto.identifier())
                .address(dto.address())
                .phone(dto.phone())
                .build();
    }

    public ClientDTO toDto(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .gender(client.getGender())
                .age(client.getAge())
                .identifier(client.getIdentifier())
                .address(client.getAddress())
                .phone(client.getPhone())
                .build();
    }
}
