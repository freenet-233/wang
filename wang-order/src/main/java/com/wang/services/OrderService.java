package com.wang.services;


import com.wang.pojo.Order;

/**
 * @author wangguangpeng
 */
public interface OrderService {

    /**
     * 保存订单
     * @param order
     */
    public void save(Order order);
}
