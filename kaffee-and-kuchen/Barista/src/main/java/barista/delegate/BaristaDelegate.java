package barista.delegate;

import barista.message.CoffeeCompleteSource;
import barista.message.OrderMessageRequest;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.logging.Logger;

@Component("baristaDelegate")
public class BaristaDelegate implements JavaDelegate {
    private final RestTemplate restTemplate;
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Autowired
    CoffeeCompleteSource source;

    public BaristaDelegate(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

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

        URI uri = URI.create("http://localhost:8083/messageForCashier/");
        OrderMessageRequest request = new OrderMessageRequest();
        request.orderMessage = orderInfo;
        request.orderName = orderName;
        request.orderId = orderId;
        restTemplate.put(uri, request);
        source.publish().send(MessageBuilder.withPayload(request).build());
        LOGGER.info(orderName + " Ready!");

    }

}
