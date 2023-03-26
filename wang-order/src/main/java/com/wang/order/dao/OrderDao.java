package com.wang.order.dao;

import com.wang.order.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangguangpeng
 */
public interface OrderDao extends JpaRepository<Order, Integer> {
}
