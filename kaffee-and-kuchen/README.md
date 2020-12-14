# Kaffee and Kuchen
Spring Boot Application using [Camunda](http://docs.camunda.org).

## Show me the important parts!
[BPMN Process](KaffeeAndKuchenCollaboration.bpmn)

![BPMN Process](KaffeeAndKuchenCollaboration.png)

## How does it work?

This Example is the implementation used in the Camunda for Architects training using RabbitMQ as a message queue in a Camunda BPM environment.  In this case, this is a Spring Boot implementation that utilizes the following Spring packages:

- Spring AMQP (https://spring.io/projects/spring-amqp)
- Spring Cloud Stream (https://spring.io/projects/spring-cloud-stream)

***This particular example requires that you have RabbitMQ running locally, using the default ports.  This can easily be handled by running RabbitMQ in a Docker container useing the following:***

```
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

### Usage

1. Run each of the microservices as Spring Boot applications
2. The Controller application also includes the UI.  It should be running on port 8083.  Navigate to http://localhost:8083/index.html
3. Each of the microservices have the Camunda webapps running on thier respective ports.

### Code Structure

For each message, there will be a source file for the following:

- **Source Interface.**  This defines an output channel for publishing a message to a topic.
- **Sink Interface**.  This defines an input channel for subscribing to a topic to receive messages.
- **Publisher class**.  Implementation of Source interface.
- **Subscriber class**.  Implementation of Sink interface.

Other notable source files:

- **Camunda Delegate classes** that publish messages to the queue.  These classes will inject implementations of the defined Source interfaces, so make them available in the execute() method.
- **application.yaml.**  This will associate topic names to defined channels.




## Environment Restrictions
Built and tested against Camunda BPM version 7.14.2.

## Known Limitations

## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

