package com.camunda.cashier;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CashierController {
    public static final String MESSAGE_NAME = "paymentRequested";
    public static final String ORDER_INFO = "orderInfo";
    public static final String ORDER_NAME = "orderName";
    public static final String ORDER_ID = "orderId";
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final RuntimeService runtimeService;

    @Autowired
    public CashierController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @StreamListener(OrderProcessor.NEW_ORDER_IN)
    public void handle(Order order) {
        log.info("Processing payment for " + order.orderName + "'s " + order.orderMessage);
        runtimeService.createMessageCorrelation(MESSAGE_NAME)
                .setVariable(ORDER_INFO, order.orderMessage)
                .setVariable(ORDER_NAME, order.orderName)
                .setVariable(ORDER_ID, order.orderId)
                .correlate();
        log.info("Payment Process Started");
    }
}
