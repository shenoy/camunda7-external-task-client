package com.example.client;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalTaskConfig {

    @Bean
    @ExternalTaskSubscription(
        topicName = "my-external-task",
        processDefinitionKey = "externaltask-process",
        includeExtensionProperties = true,
        variableNames = "defaultScore"
    )
    public ExternalTaskHandler externalTaskHandler() {
        return (externalTask, externalTaskService) -> {
            System.out.println(" Doing some business logic ");
            externalTaskService.complete(externalTask);
        };
    }
}