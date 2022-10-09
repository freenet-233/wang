package com.wang.services.impl;

import com.wang.dao.OrderDao;
import com.wang.services.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
