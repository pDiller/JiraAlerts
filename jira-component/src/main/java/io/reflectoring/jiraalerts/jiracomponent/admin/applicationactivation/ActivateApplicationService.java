package io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.JiraConnectionDataDTO;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.JiraConnectionDataService;

@Service
@ApplicationScope
public class ActivateApplicationService {

	@Autowired
	private JiraConnectionDataService jiraConnectionDataService;

	private boolean activated = false;
	private String activationPassword;

	public boolean isApplicationActivated() {
		return activated;
	}

	public void activateApplication(String activationPassword) {
		this.activationPassword = activationPassword;

		JiraConnectionDataDTO jiraConnectionDataDTO = jiraConnectionDataService.getJiraConnectionDataDTO();

		activated = true;
	}

	public String getActivationPassword() {
		return activationPassword;
	}
}
