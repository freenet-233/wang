package com.wang.learn.javaeight;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * StreamDemo
 *
 * @author wangguangpeng
 * @version 1.0
 * 2022/1/21 14:35
 * description: TODO
 **/
public class StreamDemo {
    public static void main(String[] args) {

        Product prod1 = new Product(1L, 1, new BigDecimal("15.5"), "面包", "零食");
        Product prod2 = new Product(2L, 2, new BigDecimal("20"), "饼干", "零食");
        Product prod3 = new Product(3L, 3, new BigDecimal("30"), "月饼", "零食");
        Product prod4 = new Product(4L, 3, new BigDecimal("10"), "青岛啤酒", "啤酒");
        Product prod5 = new Product(5L, 10, new BigDecimal("15"), "百威啤酒", "啤酒");
        List<Product> prodList = Lists.newArrayList(prod1, prod2, prod3, prod4, prod5);

        /**
         * 按照类目分组
         */
        Map<String, List<Product>> prodMap = prodList.stream().collect(Collectors.groupingBy(Product::getCategory));
        System.out.println(new Gson().toJson(prodMap));

        /**
         *
         */

    }

}
