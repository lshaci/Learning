package com.lshaci.rocketmq.balance;

import com.lshaci.rocketmq.MessageListenerImpl;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

public class Consumer1 {

    public static void main(String[] args) throws MQClientException {

        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("HELLO_CONSUMER");

        // Specify name server addresses.
        consumer.setNamesrvAddr("127.0.0.1:9876;127.0.0.1:9877");
        // Subscribe one more more topics to consume.
        consumer.subscribe("HelloTopic", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerImpl());

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer1 Started.%n");
    }
}
