package org.camunda.kaffee.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.kaffee.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static org.camunda.kaffee.Constants.*;

@Component(value = "tellCashierAboutOrder")
public class TellCashierSomethingDelegate implements JavaDelegate {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final MessageService messageService;

    @Autowired
    public TellCashierSomethingDelegate(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderMessage = (String) execution.getVariable(ORDER_MESSAGE);
        String orderName = (String) execution.getVariable(ORDER_NAME);
        Integer orderId = (Integer) execution.getVariable(ORDER_ID);
        messageService.sendMessage(orderMessage, orderName, orderId);
        log.info("Sending message that " + orderMessage + " for " + orderName);
    }

}
