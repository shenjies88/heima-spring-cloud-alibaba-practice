package com.heima;

import com.heima.dao.TxLogDao;
import com.heima.entity.Order;
import com.heima.entity.TxLog;
import com.heima.service.OrderService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

/**
 * @author shenjies88
 * @since 2021/5/24-10:05 下午
 */
@RocketMQTransactionListener
public class TxMessageListener implements RocketMQLocalTransactionListener {

    @Autowired
    private TxLogDao txLogDao;

    @Autowired
    private OrderService orderService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            orderService.createOrder((String) msg.getHeaders().get("txId"), (Order) arg);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        TxLog txLog = txLogDao.findById((String) msg.getHeaders().get("txId")).orElse(null);
        if (txLog == null) {
            return RocketMQLocalTransactionState.COMMIT;
        } else {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
