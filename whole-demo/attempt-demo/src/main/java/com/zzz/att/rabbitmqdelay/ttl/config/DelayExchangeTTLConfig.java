package com.zzz.att.rabbitmqdelay.ttl.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangjiaw
 * @date 2021-06-09 11:35
 */
@Configuration
public class DelayExchangeTTLConfig {

    /**
     * 死信交换机
     * @return
     */
    @Bean
    public DirectExchange delayTTLExchange(){
        return new DirectExchange("ttl.delay_exchange");
    }

    /**
     * 死信队列
     * @return
     */
    @Bean
    public Queue delayTTLQueue(){
        Map<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", "ttl.delay_receive_exchange");
        map.put("x-dead-letter-routing-key", "ttl.delay_receive_key");
        return new Queue("ttl.delay_queue", true, false, false, map);
    }

    /**
     * 死信队列绑定死信交换机
     * @return
     */
    @Bean
    public Binding delayTTLBinding() {
        return BindingBuilder.bind(delayTTLQueue()).to(delayTTLExchange()).with("ttl.delay_key");
    }

    /**
     * 死信接收交换机
     * @return
     */
    @Bean
    public DirectExchange delayTTLReceiveExchange(){
        return new DirectExchange("ttl.delay_receive_exchange");
    }

    /**
     * 死信接收队列
     * @return
     */
    @Bean
    public Queue delayTTLReceiveQueue() {
        return new Queue("ttl.delay_receive_queue");
    }

    /**
     * 死信接收队列绑定死信接收交换机
     * @return
     */
    @Bean
    public Binding delayTTLReceiveBinding(){
        return BindingBuilder.bind(delayTTLReceiveQueue()).to(delayTTLReceiveExchange()).with("ttl.delay_receive_key");
    }

}
