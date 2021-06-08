package com.zzz.att.rabbitmq;

import com.zzz.att.rabbitmq.config.RabbitmqConfirmCallback;
import com.zzz.att.rabbitmq.entity.MessageEntity;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitmqConfirmCallback rabbitmqConfirmCallback;

    @GetMapping("/rabbitmq/send")
    public String sendDirectMessage(){
        MessageEntity testEntity = new MessageEntity();
        testEntity.setId(1L);
        testEntity.setContent("content");
        String msgId = UUID.randomUUID().toString().replaceAll("-", "");
        testEntity.setUuid(msgId);
        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.setConfirmCallback(rabbitmqConfirmCallback);
        rabbitTemplate.setReturnCallback(rabbitmqConfirmCallback);
        rabbitTemplate.convertAndSend("exchange_test", "entity.test.log", testEntity, correlationData);
        return "send success";
    }

}
