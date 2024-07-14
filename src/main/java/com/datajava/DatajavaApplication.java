package com.datajava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; 
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableJpaRepositories("com.datajava.repository") // Ajoutez cette ligne pour scanner le package repository
public class DatajavaApplication {

    private static final Logger logger = LoggerFactory.getLogger(DatajavaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DatajavaApplication.class, args);
        logger.info("Application started successfully");
    }
    @Bean
    public HttpMessageConverter<Object> jsonHttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
}
