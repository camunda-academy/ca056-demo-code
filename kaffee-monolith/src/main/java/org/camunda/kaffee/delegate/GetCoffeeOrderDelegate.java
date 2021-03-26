package org.camunda.kaffee.delegate;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static org.camunda.kaffee.Constants.ORDER_INFO;

@Component("getCoffeeOrder")
public class GetCoffeeOrderDelegate implements JavaDelegate {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    long waitTimeMillis = 1000;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int loops = 10;
        String orderInfo = (String) execution.getVariable(ORDER_INFO);
        if (orderInfo.contains("soy")) {
            throw new BpmnError("NoSoy");
        }
        log.info("We're Working on it! -- ");
        while(loops >= 0) {
            System.out.print("..");
            Thread.sleep(waitTimeMillis);
            loops--;
        }
        log.info("Coffee is done! -- ");
    }
}
