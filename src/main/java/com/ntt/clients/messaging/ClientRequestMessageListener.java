package com.ntt.clients.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.clients.dto.ClientDTO;
import com.ntt.clients.mapper.ClientMapper;
import com.ntt.clients.repositories.ClientRepository;
import com.ntt.clients.util.ClientCorrelationPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientRequestMessageListener implements MessageListener {
    private final RedisMessagePublisher publisher;

    private final ObjectMapper objectMapper;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        var rawPayload = new String(message.getBody());
        log.info("Received request: {}", rawPayload);

        var payload = new ClientCorrelationPayload(rawPayload);
        var responseChannel = "response:" + payload.getCorrelationId();
        var responsePayload = findClientById(payload.getClientId())
                .flatMap(this::marshal)
                .orElse("");

        log.info("Sending client with id {} to channel {}.", payload.getClientId(), responseChannel);
        publisher.publish(responseChannel, responsePayload);
    }

    private Optional<ClientDTO> findClientById(Long clientId) {
        return clientRepository.findByIdAndStatus(clientId, true)
                .map(clientMapper::toDto);
    }

    private Optional<String> marshal(ClientDTO clientDTO) {
        try {
            return Optional.of(objectMapper.writeValueAsString(clientDTO));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }
}
