package com.ntt.clients.util;

import lombok.Getter;

@Getter
public class ClientCorrelationPayload {
    private static final int EXPECTED_PAYLOAD_PARTS = 3;

    private final String correlationId;
    private final Long clientId;

    public ClientCorrelationPayload(String requestPayload) {
        var parts = requestPayload.split(":");

        if (parts.length != EXPECTED_PAYLOAD_PARTS) {
            throw new RuntimeException("Invalid correlation payload");
        }

        correlationId = parts[1];
        clientId = Long.parseLong(parts[2]);
    }
}
