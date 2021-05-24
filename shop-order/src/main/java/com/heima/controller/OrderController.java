package com.heima.controller;

import com.alibaba.fastjson.JSON;
import com.heima.client.ProductClient;
import com.heima.entity.Order;
import com.heima.entity.Product;
import com.heima.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");
        Product product = productClient.findByPid(pid);
        log.info(">>商品信息，查询结果:" + JSON.toJSONString(product));
        if (product == null) {
            return null;
        }
        if (product.getPid() == -1) {
            Order order = new Order();
            order.setPname("下单失败");
            return order;
        }
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        rocketMQTemplate.convertAndSend("order-topic", order);
        sendTxMessage(order);
        return order;
    }

    private void sendTxMessage(Order order) {
        String txId = UUID.randomUUID().toString();
        rocketMQTemplate.sendMessageInTransaction("tx_topic",
                MessageBuilder.withPayload(order).setHeader("txId", txId).build(), order);
    }
}

