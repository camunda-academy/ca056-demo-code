package barista.message;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(CoffeeCompleteSource.class)
public class CoffeeCompletePublisher {
}
