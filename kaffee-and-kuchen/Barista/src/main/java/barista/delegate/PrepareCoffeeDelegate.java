package barista.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("prepareCoffee")
public class PrepareCoffeeDelegate implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    long waitTimeMillis = 1000;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        int loops = 10;
        String orderInfo = (String) execution.getVariable("orderInfo");
        String orderName = (String) execution.getVariable("orderName");
        LOGGER.info("We're Working on it! -- "+ orderInfo);
        while(loops >= 0) {
            System.out.print("..");
            Thread.sleep(waitTimeMillis);
            loops--;
        }
        System.out.println();
        LOGGER.info("We've done it! -- "+ orderInfo);
    }
}
