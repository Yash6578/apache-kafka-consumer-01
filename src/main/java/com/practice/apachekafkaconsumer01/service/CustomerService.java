package com.practice.apachekafkaconsumer01.service;

import com.practice.apachekafkaconsumer01.constants.KafkaConstants;
import com.practice.apachekafkaconsumer01.model.Customer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    @KafkaListener(topics = KafkaConstants.CUSTOMER_TOPIC, groupId = KafkaConstants.GROUP_ID)
    public Customer listener(Customer customer) {
        System.out.println("Data Received from kafka topic" + customer);
        return customer;
    }
}
