package cashier.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface NewOrderSink {

    String channel = "newOrderInputChannel";

    @Input(NewOrderSink.channel)
    SubscribableChannel submitOrder();

}
