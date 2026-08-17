package com.example.mongodbConnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.example.mongodbConnect.service.TaskService;

@SpringBootApplication
public class MongodbConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbConnectApplication.class, args);
    }

    @Bean
    CommandLineRunner init(TaskService taskService) {
        return args -> {
            taskService.createTaskItems();
        };
    }
}
