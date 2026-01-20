package com.xyz.moviebooking.common.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaJsonProducerConfig {

    @Bean
    public ProducerFactory<String, Object> jsonProducerFactory(KafkaProperties kafkaProperties) {

        Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties());

        props.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                org.springframework.kafka.support.serializer.JsonSerializer.class
        );
        return new DefaultKafkaProducerFactory<>(props);
    }

    //Custom KafkaTemplate<String, Object>
    @Bean
    public KafkaTemplate<String, Object> jsonKafkaTemplate(ProducerFactory<String, Object> jsonProducerFactory) {
        return new KafkaTemplate<>(jsonProducerFactory);
    }
}

