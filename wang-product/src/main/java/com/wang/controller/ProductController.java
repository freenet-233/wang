package com.wang.controller;

import com.google.gson.Gson;
import com.wang.common.pojo.Product;
import com.wang.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangguangpeng
 */
@RestController
@Slf4j
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid) {
        Product product = productService.getProductById(pid);
        Gson gson = new Gson();
        log.info("查询到商品：" + gson.toJson(product));
        return product;
    }

}
