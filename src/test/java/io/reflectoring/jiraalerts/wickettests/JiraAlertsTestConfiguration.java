package io.reflectoring.jiraalerts.wickettests;

import static org.mockito.Mockito.mock;

import io.reflectoring.jiraalerts.jiracomponent.configuration.JiraConnectionConfigurationService;
import io.reflectoring.jiraalerts.jiracomponent.connection.persistence.JiraConnectionDataRepository;
import io.reflectoring.jiraalerts.shared.wickettests.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
