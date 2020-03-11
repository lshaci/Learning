package com.lshaci.rocketmq.hello;

import com.lshaci.rocketmq.MessageListenerImpl;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

public class Consumer {

    public static void main(String[] args) throws MQClientException {

        // Instantiate with specified provider group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_name");

        // Specify name server addresses.
        consumer.setNamesrvAddr("127.0.0.1:9876;127.0.0.1:9877");
        // 批量消费消息条数的最大限制
        consumer.setConsumeMessageBatchMaxSize(10);
        // Subscribe one more more topics to consume.
        consumer.subscribe("HelloTopic", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerImpl());

        //Launch the provider instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
