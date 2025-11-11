package org.example.payment_container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.example")
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "org.example.payment_dataaccess.repository")
@EntityScan(basePackages = "org.example.payment_dataaccess.entity")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("🚀 Payment Service is running...");
    }
}

