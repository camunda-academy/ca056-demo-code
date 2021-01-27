package com.camunda.ordersorter;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface OrderProcessor {
    String SORT_ORDER_IN = "sort-order"; // Topic where the orders to be sorted appear
    String COFFEE_COMPLETE_IN = "coffee-complete"; // Topic where notifications that coffee is ready appear
    String ORDER_COMPLETE_OUT = "order-complete"; // Topic where the completed orders are sent
    String BARISTA_OUT = "notify-barista"; // Topic where barista notifications are sent

    @Input(SORT_ORDER_IN)
    SubscribableChannel sourceOfOrdersToSort();

    @Input(COFFEE_COMPLETE_IN)
    SubscribableChannel sourceOfCoffeeComplete();

    @Output(ORDER_COMPLETE_OUT)
    MessageChannel completeOrder();

    @Output(BARISTA_OUT)
    MessageChannel sendToBarista();
}
