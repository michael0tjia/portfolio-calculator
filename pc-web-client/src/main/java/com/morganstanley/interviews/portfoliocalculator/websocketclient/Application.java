package com.morganstanley.interviews.portfoliocalculator.websocketclient;

import com.morganstanley.interviews.portfoliocalculator.websocketclient.model.Portfolio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder.build();
    }
    
    @Bean
    public CommandLineRunner run(final RestTemplate restTemplate) throws Exception {
        return args -> {
            final Portfolio portfolio = restTemplate.getForObject(
                    "http://localhost:8080/portfolio", Portfolio.class);
            System.out.println(portfolio.toString());
            System.exit(0);
        };
    }
}