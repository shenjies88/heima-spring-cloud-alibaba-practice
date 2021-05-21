package com.heima.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author shenjies88
 * @since 2021/5/21-4:29 下午
 */
@Slf4j
@Service
public class SentinelOrderService {

    @SentinelResource("message")
    public void message() {
        log.info("message");
    }
}
