package com.wang.learn.javaeight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Product
 *
 * @author wangguangpeng
 * @version 1.0
 * 2022/1/21 14:36
 * description: TODO
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private Integer num;
    private BigDecimal price;
    private String name;
    private String category;

}
