package com.camunda.barista;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class BaristaController {
    public static final String ORDER_INFO = "orderInfo";
    public static final String ORDER_NAME = "orderName";
    public static final String ORDER_ID = "orderId";
    public static final String NOTIFY_BARISTA_MESSAGE = "notifyBaristaMessage";
    public static final String DELIVER_BARISTA_PROCESS = "deliverBaristaProcess";
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final RuntimeService runtimeService;

    @Autowired
    public BaristaController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @StreamListener(OrderProcessor.BARISTA_IN)
    public void handle(Order order) {
        log.info("Need to make " + order.orderName + "'s coffee");
        Map<String, Object> variables = new HashMap();
        variables.put(ORDER_INFO, order.orderMessage);
        variables.put(ORDER_NAME, order.orderName);
        variables.put(ORDER_ID, order.orderId);
        runtimeService.correlateMessage(NOTIFY_BARISTA_MESSAGE, DELIVER_BARISTA_PROCESS, variables);
        log.info("Barista process message correlated for " + order.orderId);
    }
}
