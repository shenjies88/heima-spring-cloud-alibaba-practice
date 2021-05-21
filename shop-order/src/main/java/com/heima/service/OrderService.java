package com.heima.service;

import com.heima.dao.OrderDao;
import com.heima.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shenjies88
 * @since 2021/5/21-11:50 上午
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public void save(Order order) {
        orderDao.save(order);
    }
}