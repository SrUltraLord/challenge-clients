package com.ntt.clients.config;

import com.ntt.clients.messaging.ClientRequestMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfiguration {
    @Bean
    public RedisMessageListenerContainer container(
            RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        var container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new ChannelTopic("clients_channel"));

        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(ClientRequestMessageListener listener) {
        return new MessageListenerAdapter(listener, "onMessage");
    }
}
