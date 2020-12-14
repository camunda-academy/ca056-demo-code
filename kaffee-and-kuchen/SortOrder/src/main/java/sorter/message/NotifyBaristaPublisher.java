package sorter.message;

import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * The publisher to the "notifydriver" topic is the Pizza Restaurant.
 * They cooked the pizza, then notify the driver it is ready by passing a message to the queue.
 * They use the notifydriver source to establish a channel to send messages on that topic.
 * The topic name is defined in the application.yaml file, associated as a "destination" to the channel.
 */
@EnableBinding(NotifyBaristaSource.class)
public class NotifyBaristaPublisher {
}
