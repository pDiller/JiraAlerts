package io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Session;

import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.JiraConnectionDataDTO;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.JiraConnectionDataService;
import io.reflectoring.jiraalerts.jiracomponent.jiraclient.JiraRestClientService;

@Service
@ApplicationScope
public class ActivateApplicationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivateApplicationService.class);

	@Autowired
	private JiraConnectionDataService jiraConnectionDataService;

	@Autowired
	private JiraRestClientService jiraRestClientService;

	private boolean activated = false;

	public boolean isApplicationActivated() {
		return activated;
	}

	public boolean activateApplication(String activationPassword) {

		JiraConnectionDataDTO jiraConnectionDataDTO = jiraConnectionDataService.getJiraConnectionDataDTO();
		String url = jiraConnectionDataDTO.getUrl();
		String username = jiraConnectionDataDTO.getUsername();

		checkRestClientConnectionWithCredentials(url, username, activationPassword);

		return activated;
	}

	private void checkRestClientConnectionWithCredentials(String jiraConnectionUrl, String jiraUsername, String activationPassword) {
		try {
			JiraRestClient jiraRestClient = jiraRestClientService.getJiraRestClient(jiraConnectionUrl, jiraUsername, activationPassword);

			Session jiraSession = jiraRestClient.getSessionClient().getCurrentSession().claim();
			String formattedMessage = String.format("Die Applikation wurde erfolgreich von %s freigegeben!", jiraSession.getUsername());
			LOGGER.info(formattedMessage);
			activated = true;
		} catch (Exception exception) {
			LOGGER.error("Freigabe der Applikation fehlgeschlagen: ", exception);
		}
	}
}
