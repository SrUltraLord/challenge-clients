package com.ntt.clients.services;

import com.ntt.clients.dto.ClientDTO;
import com.ntt.clients.dto.ManageClientDTO;
import com.ntt.clients.exceptions.ClientNotFoundException;
import com.ntt.clients.mapper.ClientMapper;
import com.ntt.clients.models.Client;
import com.ntt.clients.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    private final PasswordEncryptor passwordEncryptor;

    public Map<String, Long> createClient(ManageClientDTO clientDto) {
        var client = clientMapper.toEntity(clientDto);
        client.setPassword(passwordEncryptor.encrypt(clientDto.password()));
        client.setStatus(true);

        var savedClient = clientRepository.save(client);

        return Map.of("id", savedClient.getId());
    }

    public ClientDTO findClientById(Long clientId) {
        var client = findClientByIdOrThrowNotFound(clientId);

        return clientMapper.toDto(client);
    }

    public Map<String, Long> updateClient(Long clientId, ManageClientDTO clientDto) {
        var client = findClientByIdOrThrowNotFound(clientId);
        client.setName(clientDto.name());
        client.setGender(clientDto.gender());
        client.setAge(clientDto.age());
        client.setIdentifier(clientDto.identifier());
        client.setAddress(clientDto.address());
        client.setPhone(clientDto.phone());
        client.setPassword(passwordEncryptor.encrypt(clientDto.password()));

        clientRepository.save(client);

        return Map.of("id", client.getId());
    }

    public Map<String, Long> deleteClient(Long clientId) {
        var client = findClientByIdOrThrowNotFound(clientId);
        client.setStatus(false); // Soft delete

        clientRepository.save(client);

        return Map.of("id", clientId);
    }

    private Client findClientByIdOrThrowNotFound(Long clientId) {
        return clientRepository.findByIdAndStatus(clientId, true)
                .orElseThrow(() -> new ClientNotFoundException("Could not find client with id " + clientId));
    }
}
