package com.practice.apachekafkaconsumer01.config;

import com.practice.apachekafkaconsumer01.constants.KafkaConstants;

import com.practice.apachekafkaconsumer01.model.Customer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class KafkaListenerConfig {
    @Bean
    public ConsumerFactory<String, Customer> consumerFactory() {
        Map<String, Object> listenerConfiguration = new HashMap<>();

        listenerConfiguration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_HOST);
        listenerConfiguration.put(ConsumerConfig.GROUP_ID_CONFIG,KafkaConstants.GROUP_ID);
        listenerConfiguration.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        listenerConfiguration.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(listenerConfiguration, new StringDeserializer(), new JsonDeserializer<>(Customer.class, false));
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Customer> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Customer> factory = new ConcurrentKafkaListenerContainerFactory<String, Customer>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
