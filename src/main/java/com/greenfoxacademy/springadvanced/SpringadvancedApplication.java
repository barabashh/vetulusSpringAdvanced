package com.greenfoxacademy.springadvanced;

import com.greenfoxacademy.springadvanced.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SpringadvancedApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringadvancedApplication.class, args);
  }

}
