package controller.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SortOrderSource {

    @Output("sortOrderOutputChannel")
    MessageChannel publish();

}
