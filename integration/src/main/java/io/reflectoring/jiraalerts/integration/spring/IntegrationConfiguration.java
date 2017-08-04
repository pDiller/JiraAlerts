package io.reflectoring.jiraalerts.integration.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.reflectoring.jiraalerts.jiracomponent.spring.JiraComponentConfiguration;

@Configuration
@Import(JiraComponentConfiguration.class)
public class IntegrationConfiguration {}
