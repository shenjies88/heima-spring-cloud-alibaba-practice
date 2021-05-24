package com.heima.service;

import com.heima.dao.OrderDao;
import com.heima.dao.TxLogDao;
import com.heima.entity.Order;
import com.heima.entity.TxLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author shenjies88
 * @since 2021/5/21-11:50 上午
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TxLogDao txLogDao;

    public void save(Order order) {
        orderDao.save(order);
    }

    @Transactional(rollbackFor = Exception.class)
    public void createOrder(String txId, Order order) {
        save(order);

        TxLog txLog = new TxLog();
        txLog.setTxLogId(txId);
        txLog.setContent("事务测试");
        txLog.setDate(new Date());
        txLogDao.save(txLog);
    }
}