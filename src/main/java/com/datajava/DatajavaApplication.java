package com.datajava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.datajava.repository") // Ajoutez cette ligne pour scanner le package repository
public class DatajavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatajavaApplication.class, args);
    }
}
