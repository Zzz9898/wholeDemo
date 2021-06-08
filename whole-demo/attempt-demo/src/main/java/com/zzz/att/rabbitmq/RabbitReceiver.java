package com.zzz.att.rabbitmq;

import com.rabbitmq.client.Channel;
import com.zzz.att.rabbitmq.entity.MessageEntity;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Component
public class RabbitReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queues_test.log",durable="true"),
            exchange = @Exchange(value = "exchange_test",ignoreDeclarationExceptions = "true"),
            key = "entity.test.log"))
    @RabbitHandler
    public void receiver(Message message, Channel channel) throws IOException {
        MessageEntity payload = byteToObject(message.getBody(), MessageEntity.class);
        System.out.println("接收到 " + payload.toString());
        Long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
    }

    @SuppressWarnings("unchecked")
    private <T> T byteToObject(byte[] bytes, Class<T> clazz) {
        T t;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            t = (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }
}
