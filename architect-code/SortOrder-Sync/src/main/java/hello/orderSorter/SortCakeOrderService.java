package hello.orderSorter;

//import org.camunda.bpm.engine.delegate.DelegateExecution;
//import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component(value = "sortCakeOrder")
//public class SortCakeOrderService implements JavaDelegate {
public class SortCakeOrderService {
    private final RestTemplate restTemplate;

    public SortCakeOrderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    long waitTimeMillis = 1000;

//    public void execute(DelegateExecution execution) throws Exception {
    public void execute() throws Exception {

            int loops = 3;
            System.out.println("We're Working on getting you Cake! -- " );
            while(loops >= 0) {
                System.out.print("..");
                Thread.sleep(waitTimeMillis);
                loops--;
            }
            System.out.println();
            System.out.println("Cake is up -- " );

        System.out.println("All good - order is complete");
    }

}

