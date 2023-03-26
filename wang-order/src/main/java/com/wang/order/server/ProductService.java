package com.wang.order.server;


/**
 * 声明调用的提供者的name
 * @author wangguangpeng
 */
//@FeignClient("service-product")
public interface ProductService {


    /**
     * 获取产品信息
     * @FeignClient + @GetMapping 完整请求路径
     * @param pid
     * @return
     */
//    @GetMapping(value = "/product/{pid}")
//    Product findByPid(@PathVariable("pid") Integer pid);
}
