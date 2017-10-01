package io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class ActivateApplicationService {

	private boolean activated = false;

	public boolean isApplicationActivated() {
		return activated;
	}
}
