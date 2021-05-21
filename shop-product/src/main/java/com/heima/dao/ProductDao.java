package com.heima.dao;

import com.heima.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shenjies88
 * @since 2021/5/21-11:40 上午
 */
public interface ProductDao extends JpaRepository<Product, Integer> {
}
