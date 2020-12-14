package sorter.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import sorter.message.NotifyBaristaSource;
import sorter.message.OrderMessageRequest;

import java.util.logging.Logger;

@Component("notifyBarista")
public class NotifyBaristaDelegate implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    @Autowired
    NotifyBaristaSource source;

    /**
     * Use Spring AMQP to send a message
     * to the queue.
     *
     * @param execution The DelegateExecution context.
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderInfo = (String) execution.getVariable("orderInfo");
        String orderName = (String) execution.getVariable("orderName");
        Integer orderId = (Integer) execution.getVariable("orderId");
        OrderMessageRequest messageRequest = new OrderMessageRequest("Coffee", orderName, orderId);
        LOGGER.info("Received message: " + orderInfo);
        source.publish().send(MessageBuilder.withPayload(messageRequest).build());
        LOGGER.info("Notify Barista Sent to RabbitMQ: " + orderInfo);
    }

}
