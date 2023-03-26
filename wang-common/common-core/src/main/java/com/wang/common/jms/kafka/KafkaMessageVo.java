package com.wang.common.jms.kafka;

import lombok.Data;

/**
 * @author wang
 */
@Data
public class KafkaMessageVo {

    private String topic;
    private String message;
}


