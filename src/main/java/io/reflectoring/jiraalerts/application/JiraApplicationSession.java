package io.reflectoring.jiraalerts.application;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class JiraApplicationSession extends AuthenticatedWebSession {

	public JiraApplicationSession(Request request) {
		super(request);
	}

	@Override
	protected boolean authenticate(String username, String password) {
		return true;
	}

	@Override
	public Roles getRoles() {
		return null;
	}
}
