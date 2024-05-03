package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@SpringBootApplication
@ComponentScans({
        @ComponentScan(basePackages = "org.example.implementations.entities"),
        @ComponentScan("org.example.web_security")
})

public class Application {
    public static void main(String args[]){
        SpringApplication.run(Application.class, args);
    }
}

