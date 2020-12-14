package sorter.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderCompleteSource {

    @Output("orderCompleteOutputChannel")
    MessageChannel publish();

}
