package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("org.example.declarations")
@ComponentScans({
        @ComponentScan("org.example.implementations.entities"),
        @ComponentScan("org.example.declarations"),
        @ComponentScan("org.example.web_security"),
        @ComponentScan("org.example.impl.services"),
        @ComponentScan("org.example.controllers")
})

public class Application {
    public static void main(String args[]){
        SpringApplication.run(Application.class, args);
    }
}

