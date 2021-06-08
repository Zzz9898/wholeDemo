package com.zzz.att.rabbitmq.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String content;

    private String uuid;
}
