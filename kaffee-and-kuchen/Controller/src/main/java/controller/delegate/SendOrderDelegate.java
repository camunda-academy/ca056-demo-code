package controller.delegate;

import controller.message.OrderMessageRequest;
import controller.message.SortOrderSource;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("sendOrderDelegate")
public class SendOrderDelegate implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Autowired
    SortOrderSource source;

    /**
     * Use Spring AMQP to send a message (the order) to the queue.
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
        LOGGER.info("Online Order Sent to RabbitMQ: " + orderInfo);

    }

}
