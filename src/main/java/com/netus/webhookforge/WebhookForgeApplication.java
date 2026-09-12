package com.netus.webhookforge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebhookForgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebhookForgeApplication.class, args);
    }

}
