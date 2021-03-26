package org.camunda.kaffee.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.kaffee.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static org.camunda.kaffee.Constants.*;

@Component("sortOrder")
public class SortOrderDelegate implements JavaDelegate {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderInfo = (String) execution.getVariable(ORDER_INFO);
        String orderName = (String) execution.getVariable(ORDER_NAME);
        Integer orderId = (Integer) execution.getVariable(ORDER_ID);
        log.info("Sorting order for " + orderName);
        if (orderInfo.contains(CAKE)) {
            log.info("Sorting cake order");
            execution.setVariable(CAKE_ORDERED, true);
        }
        if (orderInfo.contains(COFFEE)) {
            log.info("Sorting coffee order");
            execution.setVariable(COFFEE_ORDERED, true);
        }
    }
}
