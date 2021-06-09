package com.zzz.att.rabbitmqdelay.dlx;

import com.zzz.att.rabbitmq.config.RabbitmqConfirmCallback;
import com.zzz.att.rabbitmq.entity.MessageEntity;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author zhangjiaw
 * @date 2021-06-08 17:39
 */
@RestController
public class DelayRabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitmqConfirmCallback rabbitmqConfirmCallback;

    @GetMapping("/rabbitmq/delay/send")
    public String sendDelayMessage(){
        MessageEntity testEntity = new MessageEntity();
        testEntity.setId(1L);
        testEntity.setContent("content");
        String msgId = UUID.randomUUID().toString().replaceAll("-", "");
        testEntity.setUuid(msgId);
        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.setConfirmCallback(rabbitmqConfirmCallback);
        rabbitTemplate.setReturnCallback(rabbitmqConfirmCallback);
        rabbitTemplate.convertAndSend("delay_exchange", "delay_key", testEntity,
                message -> {message.getMessageProperties().setDelay(5000); return message;}, correlationData);  // 设置消息过期时间
        System.out.println("发送消息成功, 时间为：" + LocalDateTime.now().toString());
        return "send success";
    }
}
