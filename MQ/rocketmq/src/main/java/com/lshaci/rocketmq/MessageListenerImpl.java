package com.lshaci.rocketmq;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MessageListenerImpl implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        msgs.forEach(m -> {
            try {
                String body = new String(m.getBody(), RemotingHelper.DEFAULT_CHARSET);
                String topic = m.getTopic();
                String tags = m.getTags();
                System.out.println("MsgId=" + m.getMsgId());
                System.err.println("topic = " + topic + "; tags = " + tags + "; body = " + body);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
