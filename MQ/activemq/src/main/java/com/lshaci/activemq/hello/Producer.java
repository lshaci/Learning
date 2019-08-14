package com.lshaci.activemq.hello;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

/**
 * 消息生产者
 */
public class Producer {

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
        // 5.通过session创建发送消息对象
        MessageProducer producer = session.createProducer(queue);
        // 6.设置消息持久化特性setDeliveryMode
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        for (int i = 0; i < 10; i++) {
            // 7.创建消息
            TextMessage message = new ActiveMQTextMessage();
            message.setText("测试的消息" + i);
            // 8.发送消息
            producer.send(message);

        }
        System.err.println("消息已发送");
        session.close();
        producer.close();
        connection.close();
    }
}
