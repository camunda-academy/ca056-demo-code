package com.camunda.ordersorter.delegate;

import com.camunda.ordersorter.Order;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.logging.Logger;

@Component(value = "tellCashierAboutOrder")
public class TellCashierSomethingDelegate implements JavaDelegate {
    public static final String ORDER_MESSAGE = "orderMessage";
    public static final String ORDER_NAME = "orderName";
    public static final String ORDER_ID = "orderId";
    public static final String URL = "http://localhost:8083/messageForCashier/";
    private final RestTemplate restTemplate;
    private final Logger log = Logger.getLogger(this.getClass().getName());

    public TellCashierSomethingDelegate(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderMessage = (String) execution.getVariable(ORDER_MESSAGE);
        String orderName = (String) execution.getVariable(ORDER_NAME);
        Integer orderId = (Integer) execution.getVariable(ORDER_ID);
        URI uri = URI.create(URL);
        Order request = new Order(orderMessage, orderName, orderId);
        restTemplate.put(uri, request);
        log.info("Telling controller cake " + " for " + orderName + " is ready!");
    }
}
