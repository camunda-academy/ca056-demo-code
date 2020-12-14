package controller.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderCompleteSink {

    String channel = "orderCompleteInputChannel";

    @Input(OrderCompleteSink.channel)
    SubscribableChannel completeOrder();

}
