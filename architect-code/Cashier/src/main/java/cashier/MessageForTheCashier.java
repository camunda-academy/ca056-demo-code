package cashier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cashier.websockets.NotificationApplicationListener;

@RestController
public class MessageForTheCashier {
	@Autowired
	NotificationApplicationListener notificationApp;
	
    @RequestMapping(value = "/messageForCashier", method = RequestMethod.PUT)
    public String message(@RequestBody OrderMessageRequest orderMessageRequest){

        //System.out.println("We've got a pick-up of "+ orderMessageRequest.orderMessage + " for " + orderMessageRequest.orderName);
        notificationApp.notify(" -- "+ orderMessageRequest.orderMessage + " for " + orderMessageRequest.orderName + " -- <br><p>");

        return "success";

    }

}
