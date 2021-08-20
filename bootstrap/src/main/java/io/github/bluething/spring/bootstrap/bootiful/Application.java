package io.github.bluething.spring.bootstrap.bootiful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev");
        SpringApplication.run(Application.class);
    }
}
