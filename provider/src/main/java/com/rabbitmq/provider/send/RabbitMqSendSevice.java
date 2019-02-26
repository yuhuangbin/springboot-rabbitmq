package com.rabbitmq.provider.send;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * author: yu.hb
 * Date: 2019-02-26
 */
@Component
public class RabbitMqSendSevice {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDirect(Integer index) {
        String context = index + " hello " + System.currentTimeMillis();
        rabbitTemplate.convertAndSend("queueA",context);
    }

    public void sendTopic(Integer index) {
        String context = index + " hello " + System.currentTimeMillis();
        rabbitTemplate.convertAndSend("topicExchange","topic.messages",context);
    }
}
