package hello;

//import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
//@EnableProcessApplication
public class SorterSyncApplication {

    public static void main(String[] args) {

        SpringApplication.run(SorterSyncApplication.class, args);

    }

}
