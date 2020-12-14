package sorter.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component(value = "sortCakeOrder")
public class SortCakeOrderDelegate implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    long waitTimeMillis = 1000;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderInfo = (String) execution.getVariable("orderInfo");

        int loops = 3;
        LOGGER.info("We're Working on getting you Cake! for order of " + orderInfo);
        while (loops >= 0) {
            System.out.print("..");
            Thread.sleep(waitTimeMillis);
            loops--;
        }
        LOGGER.info("Cake is up -- " + orderInfo);
    }

}

