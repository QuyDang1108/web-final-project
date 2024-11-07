package com.hcmus.csdlnc.sushirestaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebFinalProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebFinalProjectApplication.class, args);
    }
}