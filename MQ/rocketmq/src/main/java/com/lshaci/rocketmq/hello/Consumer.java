package com.lshaci.rocketmq.hello;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

public class Consumer {

    public static void main(String[] args) throws MQClientException {

        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");

        // Specify name server addresses.
        consumer.setNamesrvAddr("127.0.0.1:9876;127.0.0.1:9877");

        // Subscribe one more more topics to consume.
        consumer.subscribe("HelloTopic", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.forEach(m -> {
                try {
                    String body = new String(m.getBody(), RemotingHelper.DEFAULT_CHARSET);
                    String topic = m.getTopic();
                    String tags = m.getTags();
                    System.err.println("topic = " + topic + "; tags = " + tags + "; body = " + body);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });
//            System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}