package com.camunda.barista;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface OrderProcessor {
    String BARISTA_IN = "notify-barista"; // Topic where the new coffee orders appear
    String COFFEE_COMPLETE_OUT = "coffee-complete"; // Topic where the completed coffee orders are sent

    @Input(BARISTA_IN)
    SubscribableChannel sourceOfOrdersToMake();

    @Output(COFFEE_COMPLETE_OUT)
    MessageChannel coffeeComplete();
}
