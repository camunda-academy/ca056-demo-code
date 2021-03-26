package org.camunda.kaffee;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@ProcessApplication
@SpringBootApplication
public class KaffeAndKuchenApplication extends ServletProcessApplication {
    public static void main(String[] args) {
        SpringApplication.run(KaffeAndKuchenApplication.class, args);
    }
}
