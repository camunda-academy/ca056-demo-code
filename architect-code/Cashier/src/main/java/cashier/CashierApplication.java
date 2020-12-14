package cashier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan
public class CashierApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashierApplication.class, args);
    }


}
