package com.camunda.controller;

import com.camunda.controller.websockets.NotificationApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageForTheCashier {
    public static final String URL = "/messageForCashier";
    public static final String SUCCESS = "success";
    private final NotificationApplicationListener notificationApp;

    @Autowired
	public MessageForTheCashier(NotificationApplicationListener listener) {
	    this.notificationApp = listener;
    }
	
    @RequestMapping(value = URL, method = RequestMethod.PUT)
    public String message(@RequestBody Order orderMessageRequest){
        notificationApp.notify(" -- "+ orderMessageRequest.orderMessage + " is ready for " + orderMessageRequest.orderName + " -- <br><p>");
        return SUCCESS;
    }

}
