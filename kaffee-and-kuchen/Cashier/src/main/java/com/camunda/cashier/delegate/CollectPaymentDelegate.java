package com.camunda.cashier.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("collectPayment")
public class CollectPaymentDelegate implements JavaDelegate {
    public static final String ORDER_INFO = "orderInfo";
    public static final String ORDER_NAME = "orderName";
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderInfo = (String) execution.getVariable(ORDER_INFO);
        String orderName = (String) execution.getVariable(ORDER_NAME);
        LOGGER.info("Collecting payment from " + orderName + " for: " + orderInfo);
    }

}
