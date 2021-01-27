package com.camunda.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.logging.Logger;

@Component
@RestController
public class OrderController {
    public static final String LOCAL_ORDER_UP = "/localOrderUp";
    public static final String ORDER_REQUESTED_MESSAGE = "orderRequested";
    public static final String ORDER_NAME = "orderName";
    public static final String ORDER_INFO = "orderInfo";
    public static final String ORDER_ID = "orderId";
    public static final String ORDER_COMPLETE_MESSAGE = "orderComplete";
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final RuntimeService runtimeService;

    @Autowired
    public OrderController(RuntimeService service) {
        this.runtimeService = service;
    }

    @RequestMapping(value = LOCAL_ORDER_UP, method = RequestMethod.POST)
    public String sendOrder(@RequestBody Order order) {
        log.info("-- Recieved order of " + order.orderMessage + " for " + order.orderName);
        runtimeService.createMessageCorrelation(ORDER_REQUESTED_MESSAGE)
                .setVariable(ORDER_NAME, order.orderName)
                .setVariable(ORDER_INFO, order.orderMessage)
                .setVariable(ORDER_ID, generateOrderId())
                .correlate();

        log.info("-- All good - the order has been sent -- ");
        return "Order of " + order.orderMessage + " for " + order.orderName + " has been started";
    }

    @StreamListener(OrderProcessor.ORDER_COMPLETE_IN)
    public void handle(Order order) {
        log.info("Completed order - Received: " + order.orderMessage);
        runtimeService.createMessageCorrelation(ORDER_COMPLETE_MESSAGE)
                .processInstanceVariableEquals(ORDER_ID, order.orderId)
                .correlate();
    }

    private int generateOrderId() {
        Random random = new Random();
        return random.nextInt(100000);
    }
}
