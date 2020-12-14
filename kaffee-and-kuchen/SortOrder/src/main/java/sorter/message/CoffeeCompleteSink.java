package sorter.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CoffeeCompleteSink {

    String channel = "coffeeCompleteInputChannel";

    @Input(CoffeeCompleteSink.channel)
    SubscribableChannel sortOrder();

}
