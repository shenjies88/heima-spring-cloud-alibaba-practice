package com.heima.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author shenjies88
 * @since 2021/5/21-10:57 上午
 */
//订单
@Entity(name = "shop_order")
@Data
public class Order {

    /**
     * 订单id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 商品id
     */
    private Integer pid;

    /**
     * 商品名称
     */
    private String pname;

    /**
     * 商品单价
     */
    private Double pprice;

    /**
     * 购买数量
     */
    private Integer number;
}
