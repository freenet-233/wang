package com.wang.server;


import com.wang.common.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 声明调用的提供者的name
 * @author wangguangpeng
 */
@FeignClient("service-product")
public interface ProductService {


    /**
     * 获取产品信息
     * @FeignClient + @GetMapping 完整请求路径
     * @param pid
     * @return
     */
    @GetMapping(value = "/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);
}
