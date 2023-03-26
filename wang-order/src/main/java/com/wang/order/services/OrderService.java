package com.wang.order.services;


import com.wang.order.pojo.Order;

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
