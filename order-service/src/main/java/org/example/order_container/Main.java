package org.example.order_container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.example")
@EntityScan(basePackages = "org.example.order_dataaccess.entity")
@EnableJpaRepositories(basePackages = "org.example.order_dataaccess.repository")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
