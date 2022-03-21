package com.wang.services;

import com.wang.common.pojo.Product;

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
