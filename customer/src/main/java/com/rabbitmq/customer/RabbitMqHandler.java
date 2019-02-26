package com.rabbitmq.customer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description:
 * author: yu.hb
 * Date: 2019-02-26
 */
@Component
public class RabbitMqHandler {

    /**
     * 手动ack方式处理消息
     * @param context
     * @param channel
     * @param message
     * @throws IOException
     */
    @RabbitListener(queues = "busiQueueA")
    public void directHandle(String context, Channel channel, Message message) throws IOException {
        System.out.println("queueA receive = " + context);
//        try {
//            int a = 1/0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            // 否认该消息，并重新加入队列
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
//        }
        // 消息确认
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


    /**
     * 自动确认处理消息
     * @param message
     */
    @RabbitListener(queues = "busiQueueB")
    public void topicHandle(String message) {
        System.err.println("queueB receive topic = " + message);
        int a = 1/0;
    }

    /**
     * 监听死信队列
     * @param message
     */
    @RabbitListener(queues = "deadQueue")
    public void deadQueueHandle(String message) {
        System.err.println("deadQueue receive = " + message);
    }
}
