package com.ntt.clients.messaging;

public interface MessagePublisher {
    void publish(String channel, String message);
}
