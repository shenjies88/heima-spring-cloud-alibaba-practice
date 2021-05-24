package com.heima;

import com.alibaba.fastjson.JSON;
import com.heima.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author shenjies88
 * @since 2021/5/24-9:53 下午
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "shop-user", topic = "order-topic")
public class SmsListener implements RocketMQListener<Order> {

    @Override
    public void onMessage(Order order) {
        log.info("收到一个订单信息{},发送短信", JSON.toJSONString(order));
    }
}
