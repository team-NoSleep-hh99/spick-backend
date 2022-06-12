package com.team.spick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpickApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpickApplication.class, args);
    }

}
