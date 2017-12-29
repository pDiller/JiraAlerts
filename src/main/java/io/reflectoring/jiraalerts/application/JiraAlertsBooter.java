package io.reflectoring.jiraalerts.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Springboot Application start-point.
 */
@SpringBootApplication
public class JiraAlertsBooter {

	public static void main(String[] args) {
		SpringApplication.run(JiraAlertsBooter.class, args);
	}
}
