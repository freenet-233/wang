package com.wang.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wang.common.annotation.Log;
import com.wang.common.pojo.Order;
import com.wang.common.pojo.Product;
import com.wang.common.pojo.User;
import com.wang.server.ProductService;
import com.wang.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;


/**
 * @author wangguangpeng
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private ProductService productService;


    @GetMapping("/order/prod/{pid}")
    @Log(msg = "订单日志")
    public Map<String, Object> order(@PathVariable("pid") Integer pid) {
        log.info(">>客户下单，查询商品信息");

        Product product = productService.findByPid(pid);
        log.info(">>商品信息，查询结果：{}", product.toString());
        Order order = new Order();
        order.setUid(1);
        order.setUsername("王发财");
        order.setPid(product.getPid());
        order.setPprice(product.getPprice());
        order.setPname(product.getPname());
        order.setNumber(1);
        orderService.save(order);

        Gson gson = new Gson();
        Map<String, Object> result = gson.fromJson(gson.toJson(order), new TypeToken<Map<String, Object>>() {}.getType());
        result.putAll(gson.fromJson(gson.toJson(product), new TypeToken<Map<String, Object>>() {}.getType()));
        return result;
    }

    @GetMapping("error")
    public User errorIndex() throws Exception {
        throw new Exception("访问了错误的首页");
    }
}
