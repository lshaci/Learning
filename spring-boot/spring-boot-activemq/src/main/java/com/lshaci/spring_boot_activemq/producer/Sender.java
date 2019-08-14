package com.lshaci.spring_boot_activemq.producer;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 消息发送工具
 */
public interface Sender {

    /**
     * 发送消息
     *
     * @param destination 消息目的地
     * @param message 消息内容
     */
    void sendMessage(Destination destination, String message);

    /**
     * 向queue中发送消息
     *
     * @param queue queue地址
     * @param message 消息内容
     */
    default void sendQueueMessage(Queue queue, String message) {
        sendMessage(queue, message);
    }

    /**
     * 向queue中发送消息
     *
     * @param queueName queue名称
     * @param message 消息内容
     */
    default void sendQueueMessage(String queueName, String message) {
        sendMessage(new ActiveMQQueue(queueName), message);
    }

    /**
     * 向topic中发送消息
     *
     * @param topic topic地址
     * @param message 消息内容
     */
    default void sendTopicMessage(Topic topic, String message) {
        sendMessage(topic, message);
    }

    /**
     * 向topic中发送消息
     *
     * @param topicName topic名称
     * @param message 消息内容
     */
    default void sendTopicMessage(String topicName, String message) {
        sendMessage(new ActiveMQTopic(topicName), message);
    }


}
