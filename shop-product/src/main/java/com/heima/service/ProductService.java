package com.heima.service;

import com.heima.dao.ProductDao;
import com.heima.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shenjies88
 * @since 2021/5/21-11:41 上午
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product findByPid(Integer pid) {
        return productDao.findById(pid).orElse(null);
    }
}
