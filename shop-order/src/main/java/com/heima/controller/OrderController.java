package com.heima.controller;

import com.alibaba.fastjson.JSON;
import com.heima.client.ProductClient;
import com.heima.entity.Order;
import com.heima.entity.Product;
import com.heima.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenjies88
 * @since 2021/5/21-11:53 上午
 */
@Slf4j
@RestController
public class OrderController {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");
        Product product = productClient.findByPid(pid);
        log.info(">>商品信息，查询结果:" + JSON.toJSONString(product));
        if (product == null) {
            return null;
        }
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.save(order);
        return order;
    }
}
