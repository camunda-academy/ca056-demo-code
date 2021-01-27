package com.camunda.ordersorter.delegate;

import com.camunda.ordersorter.Order;
import com.camunda.ordersorter.OrderProcessor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("notifyBarista")
public class NotifyBaristaDelegate implements JavaDelegate {
    public static final String ORDER_INFO = "orderInfo";
    public static final String ORDER_NAME = "orderName";
    public static final String ORDER_ID = "orderId";
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final OrderProcessor processor;

    @Autowired
    public NotifyBaristaDelegate(OrderProcessor orderProcessor) {
        this.processor = orderProcessor;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderInfo = (String) execution.getVariable(ORDER_INFO);
        String orderName = (String) execution.getVariable(ORDER_NAME);
        Integer orderId = (Integer) execution.getVariable(ORDER_ID);
        Order order = new Order(orderInfo, orderName, orderId);
        processor.sendToBarista().send(message(order));
        log.info("Notify Barista message sent to queue ");
    }

    private static <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}
