package com.divstar.particle.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This is the main class of the microservice.
 * 
 * @author divstar
 *
 */
@EnableJpaRepositories
@SpringBootApplication
public class Application {
	/**
	 * This method starts the Spring-application.
	 * 
	 * @param args
	 *            (String[]) command line arguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
}