package controller.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface NewOrderSource {

    @Output("newOrderOutputChannel")
    MessageChannel publish();

}
