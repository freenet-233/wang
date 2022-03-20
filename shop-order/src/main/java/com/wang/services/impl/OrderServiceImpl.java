package com.wang.services.impl;

import com.wang.dao.OrderDao;
import com.wang.pojo.Order;
import com.wang.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangguangpeng
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao OrderDao;
    @Override
    public void save(Order order) {
        OrderDao.save(order);
    }
}
