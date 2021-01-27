package com.camunda.barista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(OrderProcessor.class)
public class BaristaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaristaApplication.class, args);
    }

}
