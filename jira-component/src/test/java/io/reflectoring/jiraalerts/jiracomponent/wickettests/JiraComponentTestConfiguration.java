package io.reflectoring.jiraalerts.jiracomponent.wickettests;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.reflectoring.jiraalerts.base.wickettests.TestConfiguration;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.JiraConnectionDataDTOMapper;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.JiraConnectionDataRepository;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.JiraConnectionDataService;

@Configuration
@Import(TestConfiguration.class)
public class JiraComponentTestConfiguration {

	@Bean
	public JiraConnectionDataRepository jiraConnectionDataRepository() {
		return mock(JiraConnectionDataRepository.class);
	}

	@Bean
	public JiraConnectionDataDTOMapper jiraConnectionDataMapper() {
		return mock(JiraConnectionDataDTOMapper.class);
	}

	@Bean
	public JiraConnectionDataService jiraConnectionDataService() {
		return mock(JiraConnectionDataService.class);
	}
}
