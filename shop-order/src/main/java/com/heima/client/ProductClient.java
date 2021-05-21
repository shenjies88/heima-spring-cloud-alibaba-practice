package com.heima.client;

import com.heima.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author shenjies88
 * @since 2021/5/21-3:12 下午
 */
@FeignClient("service-product")
public interface ProductClient {

    @GetMapping(value = "/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);
}
