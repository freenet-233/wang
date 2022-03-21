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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;


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

        // TODO 模拟一次网络延迟
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //创建订单
        Order order = new Order();
        order.setUid(1);
        order.setUsername("王发财");
        order.setPid(product.getPid());
        order.setPprice(product.getPprice());
        order.setPname(product.getPname());
        order.setNumber(1);
//        orderService.save(order);

        Gson gson = new Gson();
        Map<String, Object> result = gson.fromJson(gson.toJson(order), new TypeToken<Map<String, Object>>() {}.getType());
        result.putAll(gson.fromJson(gson.toJson(product), new TypeToken<Map<String, Object>>() {}.getType()));
        log.info("创建订单成功，订单信息为{}", gson.toJson(result));
        return result;
    }

    @RequestMapping("/order/message")
    public String message(){
        return "高并发下的问题test";
    }

    @RequestMapping("/order/message1")
    public String message1(){
        return "message1";
    }

    @RequestMapping("/order/message2")
    public String message2(){
        return "message2";
    }

    @GetMapping("error")
    public User errorIndex() throws Exception {
        throw new Exception("访问了错误的首页");
    }
}
