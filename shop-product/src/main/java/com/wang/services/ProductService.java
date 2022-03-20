package com.wang.services;

import com.wang.pojo.Product;

/**
 * @author wangguangpeng
 */
public interface ProductService {

    /**
     *
     * @param id
     * @return
     */
    public Product getProductById(Integer id);
}
