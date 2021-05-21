package com.heima.dao;

import com.heima.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shenjies88
 * @since 2021/5/21-11:50 上午
 */
public interface OrderDao extends JpaRepository<Order, Long> {
}
