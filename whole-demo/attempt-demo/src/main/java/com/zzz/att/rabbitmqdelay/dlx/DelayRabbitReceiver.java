package com.zzz.att.rabbitmqdelay.dlx;

import com.rabbitmq.client.Channel;
import com.zzz.att.rabbitmq.entity.MessageEntity;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;

/**
 * @author zhangjiaw
 * @date 2021-06-08 17:35
 */
@Component
public class DelayRabbitReceiver {

    @RabbitListener(queues = "delay_queue")
    public void receiver(Message message, Channel channel) throws IOException {
        MessageEntity payload = byteToObject(message.getBody(), MessageEntity.class);
        System.out.println("接收到延迟消息" + payload.toString() + " --- 时间：" + LocalDateTime.now().toString());
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
