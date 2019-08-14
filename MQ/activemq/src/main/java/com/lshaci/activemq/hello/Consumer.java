package com.lshaci.activemq.hello;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息消费者
 */
public class Consumer {

    public static void main(String[] args) throws JMSException {
        // 1.建立ConnectionFactory
        ConnectionFactory factory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://127.0.0.1:61616"
        );
        // 2.通过factory获取Connection, 并开启
        Connection connection = factory.createConnection();
        connection.start();
        // 3.通过Connection获取session(是否开启事务，签收模式)
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        // 4.通过session创建Destination
        Queue queue = session.createQueue("test");
        // 5.通过session创建接收消息对象
        MessageConsumer consumer = session.createConsumer(queue);
        // 6.设置消息监听器
        consumer.setMessageListener(message -> {
            try {
                System.err.println("接收到的消息为：" + ((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

    }
}
