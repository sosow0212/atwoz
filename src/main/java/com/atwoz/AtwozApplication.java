package com.atwoz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaAuditing
@EnableAsync
@SpringBootApplication
@ConfigurationPropertiesScan
public class AtwozApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtwozApplication.class, args);
    }

}
