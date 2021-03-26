package org.camunda.kaffee.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static org.camunda.kaffee.Constants.*;

@Component("collectPayment")
public class CollectPaymentDelegate implements JavaDelegate {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderInfo = (String) execution.getVariable(ORDER_INFO);
        String orderName = (String) execution.getVariable(ORDER_NAME);
        log.info("Collecting payment from " + orderName + " for: " + orderInfo);
    }

}
