package com.heima.controller;

import com.heima.entity.Order;
import com.heima.entity.Product;
import com.heima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author shenjies88
 * @since 2021/5/21-11:53 上午
 */
@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        ServiceInstance serviceInstance =
                discoveryClient.getInstances("service-product").get(0);
        String url = serviceInstance.getHost() + ":" +
                serviceInstance.getPort();
        //通过restTemplate调用商品微服务
        Product product = restTemplate.getForObject(
                "http://" + url + "/product/" + pid, Product.class);
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
