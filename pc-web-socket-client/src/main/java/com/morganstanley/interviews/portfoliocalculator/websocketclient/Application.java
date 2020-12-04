package com.morganstanley.interviews.portfoliocalculator.websocketclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public NavPrinter navPrinter() {
        return new NavPrinter();
    }
}