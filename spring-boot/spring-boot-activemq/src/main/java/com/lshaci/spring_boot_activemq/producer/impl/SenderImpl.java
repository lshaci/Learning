package com.lshaci.spring_boot_activemq.producer.impl;

import com.lshaci.spring_boot_activemq.producer.Sender;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
@AllArgsConstructor
public class SenderImpl implements Sender {

    private final JmsTemplate template;

    @Override
    public void sendMessage(Destination destination, String message) {
        template.convertAndSend(destination, message);
    }
}
