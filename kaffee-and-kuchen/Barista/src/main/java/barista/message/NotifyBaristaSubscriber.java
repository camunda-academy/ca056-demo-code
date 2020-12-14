package barista.message;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@EnableBinding(NotifyBaristaSink.class)
public class NotifyBaristaSubscriber {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Autowired
    RuntimeService runtimeService;

    @StreamListener(NotifyBaristaSink.channel)
    public void handle(OrderMessageRequest order) {
        LOGGER.info("NotifyBaristaSubscriber - Received: " + order.orderMessage);
        Map<String, Object> variables = new HashMap();
        variables.put("orderInfo", order.orderMessage);
        variables.put("orderName", order.orderName);
        variables.put("orderId", order.orderId);
        runtimeService.correlateMessage("notifyBaristaMessage", "deliverBaristaProcess", variables);
    }

}
