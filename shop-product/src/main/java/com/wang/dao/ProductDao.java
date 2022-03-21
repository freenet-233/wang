package com.wang.dao;

import com.wang.common.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangguangpeng
 */
public interface ProductDao extends JpaRepository<Product, Integer> {
}
