# Kaffee and Kuchen
Spring Boot Application using [Camunda](http://docs.camunda.org).

## Show me the important parts!
[BPMN Process](KaffeeAndKuchenCollaboration.bpmn)

![BPMN Process](KaffeeAndKuchenCollaboration.png)

## How does it work?

This Example is the implementation used in the Camunda for Architects training uses RabbitMQ or Kafka as the message/event bus in a Camunda BPM microservices environment. In this case, this is a Spring Boot implementation that utilizes the following Spring package:

- Spring Cloud Stream (https://spring.io/projects/spring-cloud-stream)

By using the Spring Cloud Stream platform we can abstract the complexities of the messaging platform integration and focus on our microservices. By utilizing Spring Cloud Stream's binding libraries it is easy to switch between using RabbitMQ and Kafka. 

***This particular example requires that you have RabbitMQ or Kafka running locally, using the default ports. The included `start-bus.sh` and `stop-bus.sh` scripts use the enclosed `docker-compose.yml` to start and stop both RabbitMQ and Kafka in Docker containers and stream the output of both to the console***


### Usage
1. Run each of the microservices as Spring Boot applications. You can choose between Kafka and RabbitMQ using the profile (-P\<profile-choice\>) of either `kafka` or `rabbit`. If the `-P<profile-choice>` is not given it will default to `kafka`.
```
    ./mvnw clean package spring-boot:run -DskipTests=true -P<profile-choice>
```
2. The Controller application also includes the UI. It should be running on port 8083.  Navigate to http://localhost:8083/index.html
3. Each of the microservices have the Camunda webapps running on thier respective ports.

### Code Structure

Each service contain and interface called `OrderProcessor` which defines the input and output channels/topics. In the `@SpringBootApplication` class of each service that interface is referenced as a parameter of the `@EnableBinding` annotation.

Other notable source files:

- **Camunda Delegate classes** that publish messages to the queue. These classes will inject implementations of the defined Source interfaces, so make them available in the execute() method.
- **Controller classes** that handle the communication between the message/event bus and Camunda. This is the "glue code".

- The controller service also contains the simple UI used in the demo.


## Environment Restrictions
Built and tested against Camunda BPM version 7.14.2.

## Known Limitations

## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

