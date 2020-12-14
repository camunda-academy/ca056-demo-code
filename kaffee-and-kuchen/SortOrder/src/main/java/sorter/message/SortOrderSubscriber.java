package sorter.message;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@EnableBinding(SortOrderSink.class)
public class SortOrderSubscriber {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Autowired
    RuntimeService runtimeService;

    @StreamListener(SortOrderSink.channel)
    public void handle(OrderMessageRequest order) {
        LOGGER.info("SortOrderSubscriber - Received: " + order.orderMessage);
        Map<String, Object> variables = new HashMap();
        variables.put("orderinfo", order.orderMessage);
        runtimeService.createMessageCorrelation("newOrderMessage")
                .setVariable("orderInfo", order.orderMessage)
                .setVariable("orderName", order.orderName)
                .setVariable("orderId", order.orderId)
                .correlate();
    }

}
