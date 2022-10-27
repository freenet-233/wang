package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author wangguangpeng
 */
@SpringBootApplication
public class ProductNacosApplication {
    public static void main( String[] args ){
        SpringApplication.run(ProductNacosApplication.class, args);
    }
}
