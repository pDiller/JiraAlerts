package io.reflectoring.jiraalerts.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.reflectoring.jiraalerts.integration.spring.IntegrationConfiguration;

@Configuration
@Import(value = {IntegrationConfiguration.class, PersistenceContext.class})
public class JiraAlertsConfiguration {}
