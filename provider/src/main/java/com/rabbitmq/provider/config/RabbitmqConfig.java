package com.rabbitmq.provider.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: rabbitmq配置
 * author: yu.hb
 * Date: 2019-02-26
 */
@Configuration
public class RabbitmqConfig {

    private Exchange topicExchange;

    /**
     * 业务队列A
     * @return
     */
    @Bean
    public Queue queueA(){
        return QueueBuilder.durable("busiQueueA").withArguments(buildDeadArgs()).build();
    }

    /**
     * 业务队列B
     * @return
     */
    @Bean
    public Queue queueB(){
        return QueueBuilder.durable("busiQueueB").withArguments(buildDeadArgs()).build();
    }

    /**
     * 申明死信队列
     * @return
     */
    @Bean
    public Queue deadQueue(){
        return QueueBuilder.durable("deadQueue").build();
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    public Exchange deadExchange() {
        return ExchangeBuilder.directExchange("dead-exchange").durable(true).build();
    }

    @Bean
    public Binding bindingDeadExchangeMessages(Queue deadQueue, Exchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with("deadKey").noargs();
    }

    /**
     * topic交换机
     * @return
     */
    @Bean
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange("topicExchange").durable(true).build();
    }

    @Bean
    public Binding bindingExchangeMessages(Queue queueA, Exchange topicExchange) {
        return BindingBuilder.bind(queueA).to(topicExchange).with("topic.message").noargs();
    }

    @Bean
    public Binding bindingExchangeMessage(Queue queueB, Exchange topicExchange) {
        return BindingBuilder.bind(queueB).to(topicExchange).with("topic.#").noargs();
    }

    public Map buildDeadArgs(){
        Map<String,Object> args = new HashMap<>(2);
        args.put("x-dead-letter-exchange","dead-exchange"); // 设置死信交换机
        args.put("x-dead-letter-routing-key","deadKey"); // 死信routeKey
        return args;
    }
}
