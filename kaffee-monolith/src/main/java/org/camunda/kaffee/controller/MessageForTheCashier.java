package org.camunda.kaffee.controller;

import org.camunda.kaffee.Order;
import org.camunda.kaffee.websockets.NotificationApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class MessageForTheCashier {
    public static final String URL = "/messageForCashier";
    public static final String SUCCESS = "success";
    private final NotificationApplicationListener notificationApp;
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
	public MessageForTheCashier(NotificationApplicationListener listener) {
	    this.notificationApp = listener;
    }
	
    @RequestMapping(value = URL, method = RequestMethod.PUT)
    public String message(@RequestBody Order orderMessageRequest) {
        notificationApp.notify(" -- "+ orderMessageRequest.orderInfo + " for " + orderMessageRequest.orderName + " -- <br><p>");
        return SUCCESS;
    }

}
