package com.camunda.barista.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("prepareCoffee")
public class PrepareCoffeeDelegate implements JavaDelegate {
    public static final String ORDER_INFO = "orderInfo";
    private final Logger log = Logger.getLogger(this.getClass().getName());
    long waitTimeMillis = 1000;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int loops = 10;
        String orderInfo = (String) execution.getVariable(ORDER_INFO);
        log.info("We're Working on it! -- ");
        while(loops >= 0) {
            System.out.print("..");
            Thread.sleep(waitTimeMillis);
            loops--;
        }
        System.out.println();
        log.info("We've done it! -- ");
    }
}
