package com.camunda.ordersorter;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class OrderSorterController {
    public static final String NEW_ORDER_MESSAGE = "newOrderMessage";
    public static final String ORDER_INFO = "orderInfo";
    public static final String ORDER_NAME = "orderName";
    public static final String ORDER_ID = "orderId";
    public static final String COFFEE_COMPLETE_MESSAGE = "coffeeComplete";
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final RuntimeService runtimeService;

    @Autowired
    public OrderSorterController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @StreamListener(OrderProcessor.SORT_ORDER_IN)
    public void handleNewOrder(Order order) {
        log.info("Processing order for " + order.orderName + " for " + order.orderMessage);
        runtimeService.createMessageCorrelation(NEW_ORDER_MESSAGE)
                .setVariable(ORDER_INFO, order.orderMessage)
                .setVariable(ORDER_NAME, order.orderName)
                .setVariable(ORDER_ID, order.orderId)
                .correlate();
        log.info("Sort Order Process Started");
    }

    @StreamListener(OrderProcessor.COFFEE_COMPLETE_IN)
    public void handleCoffeeComplete(Order order) {
        log.info("Received completed barista order for " + order.orderName);
        runtimeService.createMessageCorrelation(COFFEE_COMPLETE_MESSAGE)
                .processInstanceVariableEquals(ORDER_ID, order.orderId)
                .correlate();
        log.info("Message correlated for completed order " + order.orderId);
    }
}
