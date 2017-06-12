package de.reflectoring.jiraalerts.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import de.reflectoring.jiraalerts.integration.spring.IntegrationConfiguration;

@Configuration
@Import(value = {IntegrationConfiguration.class, PersistenceContext.class})
public class JiraAlertsConfiguration {}
