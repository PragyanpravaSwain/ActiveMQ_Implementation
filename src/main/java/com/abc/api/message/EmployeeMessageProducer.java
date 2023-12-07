package com.abc.api.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMessageProducer<T>{

    private final JmsTemplate jmsTemplate;

    public EmployeeMessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String topic,T message) {
        jmsTemplate.convertAndSend(topic, message);

    }
}
