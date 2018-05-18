package io.reflectoring.jiraalerts.jiraclient;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.atlassian.jira.rest.client.api.JiraRestClient;

public class JiraRestClientServiceTest {

	private static final String JIRA_USERNAME = "username";
	private static final String JIRA_URL = "https://test.test";
	private static final String JIRA_PASSWORD = "password";

	private JiraRestClientService testSubject = new JiraRestClientService();

	@Test
	public void getJiraRestClientReturnsJiraRestClient() throws Exception {
		JiraRestClient jiraRestClient = testSubject.getJiraRestClient(JIRA_URL, JIRA_USERNAME, JIRA_PASSWORD);

		Assertions.assertThat(jiraRestClient).isNotNull();
	}

	@Test
	public void getInitializedRestClientReturnsNullWhenItsNotInitialized() throws Exception {
		JiraRestClient initializedJiraRestClient = testSubject.getInitializedJiraRestClient();
		Assertions.assertThat(initializedJiraRestClient).isNull();
	}

	@Test
	public void getInitializedRestClientReturnsTheInitializedRestClient() throws Exception {
		testSubject.getJiraRestClient(JIRA_URL, JIRA_USERNAME, JIRA_PASSWORD);

		JiraRestClient initializedJiraRestClient = testSubject.getInitializedJiraRestClient();

		Assertions.assertThat(initializedJiraRestClient).isNotNull();
	}
}
