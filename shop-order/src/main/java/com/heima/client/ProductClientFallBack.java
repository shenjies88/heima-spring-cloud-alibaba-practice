package com.heima.client;

import com.heima.entity.Product;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author shenjies88
 * @since 2021/5/21-11:19 下午
 */
@Component
public class ProductClientFallBack implements FallbackFactory<ProductClient> {
    @Override
    public ProductClient create(Throwable cause) {
        return new ProductClient() {
            @Override
            public Product findByPid(Integer pid) {
                cause.printStackTrace();
                Product product = new Product();
                product.setPid(-1);
                return product;
            }
        };
    }
}
