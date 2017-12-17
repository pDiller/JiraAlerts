package io.reflectoring.jiraalerts.jiraclient;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

@Service
public class JiraRestClientService {

	public JiraRestClient getJiraRestClient(String jiraConnectionUrl, String jiraUserName, String jiraPassword) throws URISyntaxException {
		final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
		final URI jiraServerUri = new URI(jiraConnectionUrl);
		return factory.createWithBasicHttpAuthentication(jiraServerUri, jiraUserName, jiraPassword);
	}
}
