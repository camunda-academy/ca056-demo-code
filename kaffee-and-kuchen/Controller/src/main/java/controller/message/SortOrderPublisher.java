package controller.message;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(SortOrderSource.class)
public class SortOrderPublisher {
}
