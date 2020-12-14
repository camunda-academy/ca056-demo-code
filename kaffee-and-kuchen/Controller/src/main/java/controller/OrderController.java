package controller;

import controller.message.OrderMessageRequest;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.logging.Logger;

@Component
@RestController
public class OrderController {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Autowired
    RuntimeService runtimeService;

    @RequestMapping(value = "/localOrderUp", method = RequestMethod.POST)
    public String sendOrder(@RequestBody OrderMessageRequest order){
        LOGGER.info("-- Recieved order of "+ order.orderMessage + " for " + order.orderName);
        runtimeService.createMessageCorrelation("orderRequested")
                .setVariable("orderName", order.orderName)
                .setVariable("orderInfo", order.orderMessage)
                .setVariable("orderId", generateOrderId())
                .correlate();

        LOGGER.info("-- All good - the order has been sent -- ");
        return "Order of " + order.orderMessage + " for " + order.orderName + " has been started";
    }

    private int generateOrderId() {
        Random random = new Random();
        return random.nextInt(100000);
    }


}
