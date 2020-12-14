package sorter.message;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@EnableBinding(CoffeeCompleteSink.class)
public class CoffeeCompleteSubscriber {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Autowired
    RuntimeService runtimeService;

    @StreamListener(CoffeeCompleteSink.channel)
    public void handle(OrderMessageRequest order) {
        LOGGER.info("CoffeeCompleteSubscriber - Received: " + order.orderMessage);
        Map<String, Object> variables = new HashMap();
        variables.put("orderinfo", order.orderMessage);
        runtimeService.createMessageCorrelation("coffeeComplete")
                .processInstanceVariableEquals("orderId", order.orderId)
                .correlate();
    }

}
