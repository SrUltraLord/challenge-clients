package com.ntt.clients.services;

import com.ntt.clients.dto.ManageClientDTO;
import com.ntt.clients.mapper.ClientMapper;
import com.ntt.clients.models.Client;
import com.ntt.clients.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private PasswordEncryptor passwordEncryptor;

    @Captor
    private ArgumentCaptor<String> passwordArgumentCaptor;

    @InjectMocks
    private ClientService clientService;

    @Test
    void createClient_itShouldSaveClient() {
        var dto = ManageClientDTO.builder()
                .password("Secret")
                .build();
        var mockedClient = mock(Client.class);

        when(clientMapper.toEntity(any(ManageClientDTO.class)))
                .thenReturn(mockedClient);
        when(passwordEncryptor.encrypt(anyString()))
                .thenReturn("S3cr3t");
        when(clientRepository.save(any(Client.class)))
                .thenReturn(Client.builder().id(1L).build());

        var actualResponse = clientService.createClient(dto);

        verify(mockedClient).setPassword(passwordArgumentCaptor.capture());
        verify(mockedClient).setStatus(anyBoolean());

        var expectedResponse = Map.of("id", 1L);
        assertEquals(expectedResponse, actualResponse);
        assertEquals("S3cr3t", passwordArgumentCaptor.getValue());
    }
}