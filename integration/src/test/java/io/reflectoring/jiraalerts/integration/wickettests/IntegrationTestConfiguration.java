package io.reflectoring.jiraalerts.integration.wickettests;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.reflectoring.jiraalerts.base.wickettests.TestConfiguration;
import io.reflectoring.jiraalerts.integration.admin.ApplicationStatusService;
import io.reflectoring.jiraalerts.jiracomponent.configuration.JiraConnectionConfigurationService;
import io.reflectoring.jiraalerts.jiracomponent.configuration.JiraConnectionDataMapper;
import io.reflectoring.jiraalerts.jiracomponent.configuration.persistence.JiraConnectionDataRepository;

@Configuration
@Import(TestConfiguration.class)
public class IntegrationTestConfiguration {

	@Bean
	public JiraConnectionConfigurationService jiraConnectionConfigurationService() {
		return mock(JiraConnectionConfigurationService.class);
	}

	@Bean
	public JiraConnectionDataRepository jiraConnectionDataRepository() {
		return mock(JiraConnectionDataRepository.class);
	}

	@Bean
	public ApplicationStatusService applicationStatusService() {
		return mock(ApplicationStatusService.class);
	}

	@Bean
	public JiraConnectionDataMapper jiraConnectionDataMapper() {
		return mock(JiraConnectionDataMapper.class);
	}

}
