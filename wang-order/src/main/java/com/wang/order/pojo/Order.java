package com.wang.order.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


import java.io.Serializable;

/**
 * @author wangguangpeng
 */
@Entity(name = "shop_order")
@Data
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    private Integer uid;

    private String username;

    private Integer pid;

    private String pname;

    private Double pprice;

    private Integer number;


}
