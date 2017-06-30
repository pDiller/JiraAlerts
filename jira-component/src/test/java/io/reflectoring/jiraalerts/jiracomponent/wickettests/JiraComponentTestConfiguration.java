package io.reflectoring.jiraalerts.jiracomponent.wickettests;

import io.reflectoring.jiraalerts.jiracomponent.configuration.JiraConnectionConfigurationService;
import io.reflectoring.jiraalerts.jiracomponent.connection.persistence.JiraConnectionDataRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class JiraComponentTestConfiguration {

    @Bean
    public JiraConnectionConfigurationService jiraConnectionConfigurationService(){
        return mock(JiraConnectionConfigurationService.class);
    }

    @Bean
    public JiraConnectionDataRepository jiraConnectionDataRepository() {
        return mock(JiraConnectionDataRepository.class);
    }
}
