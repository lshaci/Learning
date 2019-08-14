package com.lshaci.spring_boot_activemq;

import com.lshaci.spring_boot_activemq.producer.Sender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ActivemqApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(ActivemqApplication.class, args);

        Sender sender = context.getBean(Sender.class);

        for (int i = 0; i < 10; i++) {
            sender.sendQueueMessage("queue", "向queue发送的消息===>" + i);

            sender.sendTopicMessage("topic", "向topic发送的消息===>" + i);
            TimeUnit.SECONDS.sleep(2);
        }
    }

}
