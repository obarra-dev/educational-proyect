package com.obarra.springbootredis.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

public class MessagePublisher {

    private RedisTemplate<String, Object> redisTemplate;

    private ChannelTopic channelTopic;



    public MessagePublisher(final RedisTemplate redisTemplate, final ChannelTopic channelTopic) {
        this.redisTemplate = redisTemplate;
        this.channelTopic = channelTopic;
    }

    public void publish(final String message) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }

}
