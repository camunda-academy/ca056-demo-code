package sorter.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import sorter.message.NotifyBaristaSource;
import sorter.message.OrderCompleteSource;
import sorter.message.OrderMessageRequest;

import java.util.logging.Logger;

@Component("orderComplete")
public class OrderCompleteDelegate implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    @Autowired
    OrderCompleteSource source;

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
        OrderMessageRequest messageRequest = new OrderMessageRequest(orderInfo, orderName, orderId);
        source.publish().send(MessageBuilder.withPayload(messageRequest).build());
        LOGGER.info("Notify Order Complete Sent to RabbitMQ: " + orderInfo);
    }

}
