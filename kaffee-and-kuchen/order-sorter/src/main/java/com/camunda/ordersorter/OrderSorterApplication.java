package com.camunda.ordersorter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(OrderProcessor.class)
public class OrderSorterApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderSorterApplication.class, args);
	}

}
