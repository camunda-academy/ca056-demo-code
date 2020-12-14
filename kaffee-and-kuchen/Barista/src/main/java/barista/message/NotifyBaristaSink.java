package barista.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface NotifyBaristaSink {

    String channel = "notifyBaristaInputChannel";

    @Input(NotifyBaristaSink.channel)
    SubscribableChannel submitOrder();
}
