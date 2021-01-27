package com.camunda.cashier;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface OrderProcessor {
    String NEW_ORDER_IN = "new-order"; // Topic where the new orders appear
    String ORDER_COMPLETE_OUT = "order-complete"; // Topic where the completed orders appear

    @Input(NEW_ORDER_IN)
    SubscribableChannel sourceOfNewOrders();

    @Output(ORDER_COMPLETE_OUT)
    MessageChannel orderComplete();
}
