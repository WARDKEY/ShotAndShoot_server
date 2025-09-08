package com.example.ShotAndShoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication()
@EnableJpaAuditing
public class ShotAndShootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShotAndShootApplication.class, args);
    }

}
