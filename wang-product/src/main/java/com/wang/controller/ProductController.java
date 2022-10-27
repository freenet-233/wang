package com.wang.controller;

import com.google.gson.Gson;
import com.wang.pojo.Product;
import com.wang.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangguangpeng
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/reduct")
    public String product() {
        log.info("减库存");
//        Product product = productService.getProductById(pid);
//        Gson gson = new Gson();
//        log.info("查询到商品：" + gson.toJson(product));
        return "减库存完成";
    }

}
