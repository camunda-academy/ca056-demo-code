package org.camunda.kaffee.service;

import org.camunda.kaffee.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class MessageService {
    public static final String URL = "http://localhost:8080/messageForCashier/";
    private final RestTemplate restTemplate;

    @Autowired
    public MessageService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void sendMessage(String orderMessage, String orderName, Integer orderId) {
        URI uri = URI.create(URL);
        Order request = new Order(orderMessage, orderName, orderId);
        restTemplate.put(uri, request);
    }
}
