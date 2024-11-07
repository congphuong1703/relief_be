package com.relief;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
public class ReliefApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReliefApplication.class, args);
    }

}
