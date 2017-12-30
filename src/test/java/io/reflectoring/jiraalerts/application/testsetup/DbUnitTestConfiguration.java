package io.reflectoring.jiraalerts.application.testsetup;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.reflectoring.jiraalerts.application.spring.JiraAlertsConfiguration;

@Configuration
@Import({ JiraAlertsConfiguration.class })
@EnableAutoConfiguration(exclude = FlywayAutoConfiguration.class)
public class DbUnitTestConfiguration {}
