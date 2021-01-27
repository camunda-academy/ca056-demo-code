package com.camunda.controller.delegate;

import com.camunda.controller.Order;
import com.camunda.controller.OrderProcessor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("sendOrderDelegate")
public class SendOrderDelegate implements JavaDelegate {
    public static final String ORDER_INFO = "orderInfo";
    public static final String ORDER_NAME = "orderName";
    public static final String ORDER_ID = "orderId";
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final OrderProcessor processor;

    @Autowired
    public SendOrderDelegate(OrderProcessor orderProcessor) {
        this.processor = orderProcessor;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderInfo = (String) execution.getVariable(ORDER_INFO);
        String orderName = (String) execution.getVariable(ORDER_NAME);
        Integer orderId = (Integer) execution.getVariable(ORDER_ID);
        Order order = new Order(orderInfo, orderName, orderId);
        processor.sortOrder().send(message(order));
        log.info("Online Order Sent to queue for sorting: " + orderInfo);
    }

    private static <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }

}
