package com.wang.controller;

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

    @GetMapping("/reduce")
    public String product() {
        log.info("减库存");

        return "减库存完成";
    }

}
