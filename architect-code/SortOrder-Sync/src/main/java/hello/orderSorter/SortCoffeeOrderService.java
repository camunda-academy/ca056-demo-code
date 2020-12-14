package hello.orderSorter;

//import org.camunda.bpm.engine.delegate.BpmnError;
//import org.camunda.bpm.engine.delegate.DelegateExecution;
//import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component(value = "sortCoffeeOrder")
//public class SortCoffeeOrderService implements JavaDelegate {
public class SortCoffeeOrderService {
    private final RestTemplate restTemplate;

    public SortCoffeeOrderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

//    public void execute(DelegateExecution execution){
    public void execute(){
        String message = "Coffee Needed";
//        String message = (String) execution.getVariable("message");
//        if (message.contains("soy")) {
//            throw new BpmnError("NoSoy");
//        }

        URI uri = URI.create("http://localhost:8081/WorkIt/");
        OrderMessageRequest request = new OrderMessageRequest();
        request.orderMessage = message;
        restTemplate.put(uri, request);
        System.out.println("I've sent an order for coffee");
    }

}
