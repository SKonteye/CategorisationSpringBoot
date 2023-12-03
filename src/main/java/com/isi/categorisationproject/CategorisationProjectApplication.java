package com.isi.categorisationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class CategorisationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategorisationProjectApplication.class, args);
    }

}
