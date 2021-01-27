package com.camunda.controller;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface OrderProcessor {
    String ORDER_COMPLETE_IN = "order-complete"; // Topic where the completed orders appear
    String NEW_ORDER_OUT = "new-order"; // Topic where the new orders are sent
    String SORT_ORDER_OUT = "sort-order"; // Topic where the orders to be sorted are sent

    @Input(ORDER_COMPLETE_IN)
    SubscribableChannel sourceOfCompletedOrders();

    @Output(NEW_ORDER_OUT)
    MessageChannel newOrder();

    @Output(SORT_ORDER_OUT)
    MessageChannel sortOrder();
}
