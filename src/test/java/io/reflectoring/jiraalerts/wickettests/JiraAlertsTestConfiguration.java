package io.reflectoring.jiraalerts.wickettests;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.reflectoring.jiraalerts.base.wickettests.TestConfiguration;
import io.reflectoring.jiraalerts.jiracomponent.configuration.JiraConnectionConfigurationService;
import io.reflectoring.jiraalerts.jiracomponent.configuration.persistence.JiraConnectionDataRepository;

@Configuration
@Import(TestConfiguration.class)
public class JiraAlertsTestConfiguration {

	@Bean
	public JiraConnectionConfigurationService jiraConnectionConfigurationService() {
		return mock(JiraConnectionConfigurationService.class);
	}

	@Bean
	public JiraConnectionDataRepository jiraConnectionDataRepository() {
		return mock(JiraConnectionDataRepository.class);
	}
}
