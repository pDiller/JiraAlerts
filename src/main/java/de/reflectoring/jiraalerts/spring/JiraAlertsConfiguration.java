package de.reflectoring.jiraalerts.spring;

import de.reflectoring.jiraalerts.integration.spring.IntegrationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(IntegrationConfiguration.class)
public class JiraAlertsConfiguration {
}
