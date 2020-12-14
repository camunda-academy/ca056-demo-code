package barista.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CoffeeCompleteSource {

    @Output("coffeeCompleteOutputChannel")
    MessageChannel publish();

}
