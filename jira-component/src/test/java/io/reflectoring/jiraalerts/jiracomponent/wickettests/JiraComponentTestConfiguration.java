package io.reflectoring.jiraalerts.jiracomponent.wickettests;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.reflectoring.jiraalerts.jiracomponent.configuration.JiraConnectionConfigurationService;
import io.reflectoring.jiraalerts.jiracomponent.configuration.JiraConnectionDataMapper;
import io.reflectoring.jiraalerts.jiracomponent.configuration.persistence.JiraConnectionDataRepository;

@Configuration
public class JiraComponentTestConfiguration {

	@Bean
	public JiraConnectionConfigurationService jiraConnectionConfigurationService() {
		return mock(JiraConnectionConfigurationService.class);
	}

	@Bean
	public JiraConnectionDataRepository jiraConnectionDataRepository() {
		return mock(JiraConnectionDataRepository.class);
	}

	@Bean
	public JiraConnectionDataMapper jiraConnectionDataMapper() {
		return mock(JiraConnectionDataMapper.class);
	}

}
