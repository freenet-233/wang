package com.wang.common.jms.kafka;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author wang
 */
@Slf4j
@Component
public class KafkaPushProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * kafka的信息push发送
     *
     * @param kafkaMessageVo kafka信息对象
     * @return 推送结果
     */
    public void sendMsg(KafkaMessageVo kafkaMessageVo) {

        String topic = kafkaMessageVo.getTopic();
        String msg = kafkaMessageVo.getMessage();
        log.debug(msg);

        JSON msgJson = JSONUtil.parseObj(msg);

        CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send(topic, UUID.randomUUID().toString(), msgJson);

        //执行成功回调
        completableFuture.thenAccept(result -> {
            log.debug("发送成功:{}", JSONUtil.toJsonStr(kafkaMessageVo));
        });
        //执行失败回调
        completableFuture.exceptionally(e -> {
            log.error("发送失败", JSONUtil.toJsonStr(kafkaMessageVo), e);
            return null;
        });

    }
}
