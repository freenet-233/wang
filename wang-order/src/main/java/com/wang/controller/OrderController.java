package com.wang.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wang.pojo.Order;
import com.wang.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;


/**
 * @author wangguangpeng
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/add")
//    @Log(msg = "订单日志")
    public String order() {
        log.info(">>客户下单，查询商品信息");
        String msg = restTemplate.getForObject("http://localhost:8011/product/reduct", String.class);
        log.info(">>客户完成，{}", msg);
        //创建订单
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("王发财");
//        order.setPid(1);
//        order.setPprice(12.01);
//        order.setPname("iPhone");
//        order.setNumber(1);
//        orderService.save(order);
//
//        Gson gson = new Gson();
//        Map<String, Object> result = gson.fromJson(gson.toJson(order), new TypeToken<Map<String, Object>>() {}.getType());
//        log.info("创建订单成功，订单信息为{}", gson.toJson(result));
        return msg;
    }

}
