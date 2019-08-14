package com.lshaci.spring_boot_activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {

    @JmsListener(destination = "queue", containerFactory = "jmsListenerContainerQueue")
    public void receiveQueueMsg(String message) {
        System.err.println("------监听到activemq的数据---->" + message);
    }

    @JmsListener(destination = "topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveTopicMsg1(String message) {
        System.out.println("------监听到activemq的数据1---->" + message);
    }

    @JmsListener(destination = "topic", containerFactory = "jmsListenerContainerTopic")
    public void receiveTopicMsg2(String message) {
        System.out.println("------监听到activemq的数据2---->" + message);
    }
}
