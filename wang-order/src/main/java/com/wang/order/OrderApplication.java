package com.wang.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * @author wangguangpeng
 */
@SpringBootApplication
//@EnableFeignClients
public class OrderApplication {
    public static void main( String[] args ){
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate(RestTemplateBuilder builder){
        RestTemplate restTemplate = builder.build();
        return restTemplate;
    }
}
