package org.switch2022.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.switch2022.project.repository.jpa.interfaces")
public class SwitchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwitchApplication.class);
    }
}
