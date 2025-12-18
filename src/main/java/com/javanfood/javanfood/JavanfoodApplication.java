package com.javanfood.javanfood;

import com.javanfood.javanfood.domain.customRepository.spec.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class JavanfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavanfoodApplication.class, args);
	}

}
