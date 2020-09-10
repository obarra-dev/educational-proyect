package com.obarra.springbootredis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Component;


public class MessageListener implements org.springframework.data.redis.connection.MessageListener {

    @Override
    public void onMessage(final Message message, final byte[] pattern) {
        System.out.println("Message received: "+ new String(pattern));
    }
}
