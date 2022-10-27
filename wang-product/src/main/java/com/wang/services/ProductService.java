package com.wang.services;

import com.wang.pojo.Product;

import java.util.Optional;

/**
 * @author wangguangpeng
 */
public interface ProductService {

    /**
     * 根据id查询商品
     *
     * @param id
     * @return
     */
    public Product getProductById(Integer id);
}
