package com.wang.controller;

import com.alibaba.fastjson.JSON;
import com.wang.pojo.Product;
import com.wang.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangguangpeng
 */
@RestController
@Slf4j
public class ProducController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid) {
        Product product = productService.getProductById(pid);
        log.info("查询到商品：" + JSON.toJSONString(product));
        return product;
    }

}
