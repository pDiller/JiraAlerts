package io.reflectoring.jiraalerts.integration.admin;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class ApplicationStatusService {

	private boolean activated = false;

	public boolean isApplicationActivated() {
		return activated;
	}

	public void activateApplication() {
		activated = true;
	}
}
