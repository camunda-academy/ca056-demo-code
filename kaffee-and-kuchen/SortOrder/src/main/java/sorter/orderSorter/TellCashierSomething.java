package sorter.orderSorter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sorter.message.OrderMessageRequest;

import java.net.URI;
import java.util.logging.Logger;

@Component(value = "tellCashierAboutOrder")
public class TellCashierSomething implements JavaDelegate {
    private final RestTemplate restTemplate;
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    public TellCashierSomething(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderMessage = (String) execution.getVariable("orderMessage");
        String orderName = (String) execution.getVariable("orderName");
        Integer orderId = (Integer) execution.getVariable("orderId");
        URI uri = URI.create("http://localhost:8083/messageForCashier/");
        OrderMessageRequest request = new OrderMessageRequest(orderMessage, orderName, orderId);
        restTemplate.put(uri, request);
        LOGGER.info(orderName + " Ready!");
    }
}
