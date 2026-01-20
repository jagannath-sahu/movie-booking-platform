package com.xyz.moviebooking.common.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaJsonConsumerConfig {

    @Bean
    public ConsumerFactory<String, Object> jsonConsumerFactory(KafkaProperties kafkaProperties) {

        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties());

        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                org.springframework.kafka.support.serializer.JsonDeserializer.class
        );
        props.put(
                org.springframework.kafka.support.serializer.JsonDeserializer.TRUSTED_PACKAGES, "*"
        );
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> jsonKafkaListenerContainerFactory(
            ConsumerFactory<String, Object> jsonConsumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(jsonConsumerFactory);
        return factory;
    }
}

