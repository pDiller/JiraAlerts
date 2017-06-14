package io.reflectoring.jiraalerts.integration.spring;

import io.reflectoring.jiraalerts.jiracomponent.spring.JiraComponentConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JiraComponentConfiguration.class)
public class IntegrationConfiguration {
}
