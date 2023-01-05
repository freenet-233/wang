package com.wang.common.config;

import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;

import java.util.List;

/**
 * @Author: wangguangpeng
 * @Date: 2023/1/5
 * @Description:
 */
public interface TopicListener extends MessageListener {
    List<ChannelTopic> getTopic();
}
