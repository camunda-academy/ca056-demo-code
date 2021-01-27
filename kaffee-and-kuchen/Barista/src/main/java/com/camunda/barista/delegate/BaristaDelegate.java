package com.camunda.barista.delegate;

import com.camunda.barista.Order;
import com.camunda.barista.OrderProcessor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.logging.Logger;

@Component("baristaDelegate")
public class BaristaDelegate implements JavaDelegate {
    public static final String ORDER_INFO = "orderInfo";
    public static final String ORDER_NAME = "orderName";
    public static final String ORDER_ID = "orderId";
    public static final String URL = "http://localhost:8083/messageForCashier/";
    private final RestTemplate restTemplate;
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final OrderProcessor processor;

    @Autowired
    public BaristaDelegate(OrderProcessor orderProcessor, RestTemplateBuilder restTemplateBuilder) {
        this.processor = orderProcessor;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderInfo = (String) execution.getVariable(ORDER_INFO);
        String orderName = (String) execution.getVariable(ORDER_NAME);
        Integer orderId = (Integer) execution.getVariable(ORDER_ID);

        URI uri = URI.create(URL);
        Order order = new Order("Coffee", orderName, orderId);
        restTemplate.put(uri, order);
        order.setOrderMessage(orderInfo);
        processor.coffeeComplete().send(message(order));
        log.info(orderName + " 's order is ready!");
    }

    private static <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }

}
