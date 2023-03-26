package com.wang.order.services.impl;

import com.wang.order.dao.OrderDao;
import com.wang.order.pojo.Order;
import com.wang.order.services.OrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * @author wangguangpeng
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao OrderDao;
    @Override
    public void save(Order order) {
        OrderDao.save(order);
    }
}
