package com.ntt.clients.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientTest {
    private Client client;

    @BeforeEach
    public void setUp() {
        client = Client.builder()
                .id(1L)
                .name("John Doe")
                .gender("male")
                .age(30)
                .identifier("123456789")
                .address("123 Main St")
                .phone("555-1234")
                .password("password123")
                .status(true)
                .build();
    }

    @Test
    void testClientAttributes() {
        // Verify attributes inherited from Person
        assertEquals(1L, client.getId());
        assertEquals("John Doe", client.getName());
        assertEquals("male", client.getGender());
        assertEquals(30, client.getAge());
        assertEquals("123456789", client.getIdentifier());
        assertEquals("123 Main St", client.getAddress());
        assertEquals("555-1234", client.getPhone());

        // Verify attributes specific to Client
        assertEquals("password123", client.getPassword());
        assertTrue(client.getStatus());
    }
}