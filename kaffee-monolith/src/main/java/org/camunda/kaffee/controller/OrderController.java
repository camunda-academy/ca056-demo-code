package org.camunda.kaffee.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.kaffee.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.logging.Logger;

import static org.camunda.kaffee.Constants.*;

@Component
@RestController
public class OrderController {
    public static final String LOCAL_ORDER_UP = "/localOrderUp";
    public static final String ORDER_REQUESTED_MESSAGE = "orderRequested";
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final RuntimeService runtimeService;

    @Autowired
    public OrderController(RuntimeService service) {
        this.runtimeService = service;
    }

    @RequestMapping(value = LOCAL_ORDER_UP, method = RequestMethod.POST)
    public String sendOrder(@RequestBody Order order) {
        log.info("-- Recieved order of " + order.orderInfo + " for " + order.orderName);
        runtimeService.createMessageCorrelation(ORDER_REQUESTED_MESSAGE)
                .setVariable(ORDER_NAME, order.orderName)
                .setVariable(ORDER_INFO, order.orderInfo)
                .setVariable(ORDER_ID, generateOrderId())
                .setVariable(CAKE_ORDERED, false)
                .setVariable(COFFEE_ORDERED, false)
                .correlate();

        log.info("-- All good - the order has been sent -- ");
        return "Order of " + order.orderInfo + " for " + order.orderName + " has been started";
    }

    private int generateOrderId() {
        Random random = new Random();
        return random.nextInt(100000);
    }
}
