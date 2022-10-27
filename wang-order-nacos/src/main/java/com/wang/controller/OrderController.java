package com.wang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author wangguangpeng
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/add")
//    @Log(msg = "订单日志")
    public String order() {
        log.info(">>客户下单，查询商品信息");
        String msg = restTemplate.getForObject("http://wang-product-nacos/product/reduce", String.class);
        log.info(">>客户完成，{}", msg);

        return msg;
    }

}
