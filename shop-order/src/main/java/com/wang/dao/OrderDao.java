package com.wang.dao;

import com.wang.common.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangguangpeng
 */
public interface OrderDao extends JpaRepository<Order, Integer> {
}
