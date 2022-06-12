package com.team.spick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpickApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpickApplication.class, args);
    }

}
