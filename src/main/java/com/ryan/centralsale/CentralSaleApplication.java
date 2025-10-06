package com.ryan.centralsale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CentralSaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentralSaleApplication.class, args);
    }

}
