package com.wang.common.jms.kafka;

import com.wang.common.service.BusinessServiceDemo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wang
 */
@Slf4j
@Component
public class KafkaListenConsumer {

    @Resource
    private BusinessServiceDemo businessServiceDemo;

    /**
     * 设备流水listenner
     *
     * @param records 消费信息
     * @param ack     Ack机制
     */
    @KafkaListener(topics = "${wang.analyze.device.flow.topic.consumer}")
    public void deviceFlowListen(List<ConsumerRecord> records, Acknowledgment ack) {
        log.debug("=====设备流水deviceFlowListen消费者接收信息====");
        try {
            for (ConsumerRecord record : records) {
                log.debug("---开启线程解析设备流水数据:{}", record.toString());
                //具体service里取做逻辑
                businessServiceDemo.consumeMq(record);
            }
        } catch (Exception e) {
            log.error("----设备流水数据消费者解析数据异常:{}", e.getMessage(), e);
        } finally {
            //手动提交偏移量
            ack.acknowledge();
        }
    }

}
