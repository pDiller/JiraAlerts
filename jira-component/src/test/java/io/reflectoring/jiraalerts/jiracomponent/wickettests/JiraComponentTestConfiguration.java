package io.reflectoring.jiraalerts.jiracomponent.wickettests;

import static org.mockito.Mockito.mock;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.reflectoring.jiraalerts.base.wickettests.TestConfiguration;
import io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation.ActivateApplicationService;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.*;

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

	@Bean
	public FirstConfigurationService firstConfigurationService() {
		return mock(FirstConfigurationService.class);
	}

	@Bean
	public FirstConfigurationRepository firstConfigurationRepository() {
		return mock(FirstConfigurationRepository.class);
	}

	@Bean
	public FirstConfigurationMapper firstConfigurationMapper() {
		return mock(FirstConfigurationMapper.class);
	}

	@Bean
	public ActivateApplicationService activateApplicationService() {
		ActivateApplicationService activateApplicationServiceMock = mock(ActivateApplicationService.class);
		Mockito.when(activateApplicationServiceMock.isApplicationActivated()).thenReturn(true);
		return activateApplicationServiceMock;
	}
}
