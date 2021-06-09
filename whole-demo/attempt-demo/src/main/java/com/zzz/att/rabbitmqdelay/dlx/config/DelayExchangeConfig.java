package com.zzz.att.rabbitmqdelay.dlx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangjiaw
 * @date 2021-06-08 16:45
 * 首先安装插件（rabbitmq_delayed_message_exchange-3.8.0.ez）
 * 下载这个插件后放到rabbitmq安装目录plugins下。然后进入到sbin下执行cmd命令
 * 插件下载地址：http://www.rabbitmq.com/community-plugins.html
 * rabbitmq-plugins enable rabbitmq_delayed_message_exchange
 */
@Configuration
public class DelayExchangeConfig {

    @Bean
    public Queue delayQueue(){
        return new Queue("delay_queue", true);
    }

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        //属性参数 交换机名称 交换机类型 是否持久化 是否自动删除 配置参数
        return new CustomExchange("delay_exchange", "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingDelayQueueToDelayExchange(){
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with("delay_key").noargs();
    }
}
