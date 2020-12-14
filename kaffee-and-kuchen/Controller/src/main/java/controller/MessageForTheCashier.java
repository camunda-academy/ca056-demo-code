package controller;

import controller.message.OrderMessageRequest;
import controller.websockets.NotificationApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class MessageForTheCashier {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

	@Autowired
	NotificationApplicationListener notificationApp;
	
    @RequestMapping(value = "/messageForCashier", method = RequestMethod.PUT)
    public String message(@RequestBody OrderMessageRequest orderMessageRequest){

        LOGGER.info("We've got a pick-up of "+ orderMessageRequest.orderMessage + " for " + orderMessageRequest.orderName);
        notificationApp.notify(" -- "+ orderMessageRequest.getOrderMessage() + " for " + orderMessageRequest.getOrderName() + " is ready -- <br><p>");

        return "success";

    }

}
