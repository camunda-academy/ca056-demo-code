package sorter.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface NotifyBaristaSource {

    @Output("notifyBaristaOutputChannel")
    MessageChannel publish();

}
