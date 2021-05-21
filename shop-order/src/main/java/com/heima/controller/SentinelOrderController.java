package com.heima.controller;

import com.heima.service.SentinelOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenjies88
 * @since 2021/5/21-11:53 上午
 */
@Slf4j
@RequestMapping("/sentinel")
@RestController
public class SentinelOrderController {

    @Autowired
    private SentinelOrderService sentinelOrderService;

    @GetMapping("/order/message1")
    public String message1() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sentinelOrderService.message();
        return "message1";
    }

    int i = 0;
    @GetMapping("/order/message2")
    public String message2() {
        i++;
        if (i % 3 == 0) {
            throw new RuntimeException();
        }
        return "message2";
    }
}