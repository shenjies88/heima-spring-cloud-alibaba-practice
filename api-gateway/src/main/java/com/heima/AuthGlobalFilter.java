package com.heima;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjies88
 * @since 2021/5/21-11:57 下午
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 完成判断逻辑
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (StringUtils.isBlank(token)) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("message", "接口无权限");
            DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap(JSON.toJSONString(map).getBytes());
            exchange.getResponse().setStatusCode(HttpStatus.OK);
            return exchange.getResponse().writeWith(Flux.just(dataBuffer));
        }
        //调用chain.filter继续向下游执行
        return chain.filter(exchange);
    }

    /**
     * 顺序,数值越小,优先级越高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
