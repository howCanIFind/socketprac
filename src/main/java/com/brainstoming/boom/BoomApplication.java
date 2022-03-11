package com.brainstoming.boom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoomApplication.class, args);
    }

}
