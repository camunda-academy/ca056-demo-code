package com.camunda.ordersorter.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component(value = "sortCakeOrder")
public class SortCakeOrderDelegate implements JavaDelegate {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    long waitTimeMillis = 1000;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int loops = 3;
        log.info("We're Working on getting you Cake!");
        while (loops >= 0) {
            System.out.print("..");
            Thread.sleep(waitTimeMillis);
            loops--;
        }
        log.info("Cake is up -- ");
    }

}

