package com.wang.services.impl;

import com.wang.dao.ProductDao;
import com.wang.pojo.Product;
import com.wang.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author wangguangpeng
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer id) {
        return productDao.findById(id).get();
    }
}
