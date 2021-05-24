package com.heima.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author shenjies88
 * @since 2021/5/24-10:00 下午
 */
@Entity(name = "shop_txlog")
@Data
public class TxLog {

    @Id
    private String txLogId;
    private String content;
    private Date date;
}
