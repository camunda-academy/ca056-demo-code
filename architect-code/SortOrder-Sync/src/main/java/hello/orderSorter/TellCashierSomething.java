package hello.orderSorter;

//import org.camunda.bpm.engine.delegate.DelegateExecution;
//import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component(value = "tellCashierAboutOrder")
//public class TellCashierSomething implements JavaDelegate {
public class TellCashierSomething {
//    private final RestTemplate restTemplate;
//
//    public TellCashierSomething(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }
//
//    @Override
//    public void execute(DelegateExecution execution) throws Exception {
//        String orderMessage = (String)execution.getVariable("orderMessage");
//        String orderName = execution.getProcessBusinessKey();
//
//        URI uri = URI.create("http://localhost:8082/messageForCashier/");
//        OrderMessageRequest request = new OrderMessageRequest();
//        request.orderMessage = orderMessage;
//        request.orderName = orderName;
//        restTemplate.put(uri, request);
//        System.out.println(orderName + " Ready!");
//    }
}
