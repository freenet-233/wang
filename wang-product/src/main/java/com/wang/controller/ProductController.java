package com.wang.controller;

import com.wang.services.ProductService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangguangpeng
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/reduct/{pid}")
    public String product(Integer pid) {
        log.info("减库存");
//        Product product = import javax.annotation.Resource;
        productService.getProductById(pid);
//        Gson gson = new Gson();
//        log.info("查询到商品：" + gson.toJson(product));
        return "减库存完成";
    }

}
