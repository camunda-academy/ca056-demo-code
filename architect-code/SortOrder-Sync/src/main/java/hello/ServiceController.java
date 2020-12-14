package hello;

import hello.orderSorter.OrderMessageRequest;
import hello.orderSorter.SortCakeOrderService;
import hello.orderSorter.SortCoffeeOrderService;
//import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class ServiceController {
    @Autowired
    SortCakeOrderService sortCakeOrder;

    @Autowired
    SortCoffeeOrderService sortCoffeeOrder;

    // Add access to the engine
//    @Autowired
//    RuntimeService runtimeService;

    @RequestMapping(value = "/orderUp", method = RequestMethod.POST)
    public String index(@RequestBody OrderMessageRequest orderMessageRequest) throws Exception {

        System.out.println("Received order for " + orderMessageRequest.orderName + ": " + orderMessageRequest.orderMessage);
        String orderMessage = orderMessageRequest.orderMessage.toLowerCase();

        // Sort the order
        if(orderMessageRequest.orderMessage.toLowerCase().contains("cake")){
            orderMessage = orderMessage + " Cake ";
            sortCakeOrder.execute();
        }
        if(orderMessageRequest.orderMessage.toLowerCase().contains("coffee")){
            orderMessage = orderMessage + " Coffee ";
            sortCoffeeOrder.execute();
        }

        return "Order of " + orderMessage + " Is Ready ";








        // Add process instance variables and start
//        Map<String, Object> vars = new HashMap<String, Object>();
//        vars.put("message", orderMessage);
//        String businessKey = orderMessageRequest.orderName;
//
//        runtimeService.startProcessInstanceByKey("ProcessOrder", businessKey, vars);
//
//        return "Order of " + orderMessage + " Is Being Prepared for " + businessKey;
    }
}
