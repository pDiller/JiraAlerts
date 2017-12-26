package io.reflectoring.jiraalerts.application;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import io.reflectoring.jiraalerts.application.login.UserNotLoggedInException;

public class TestSession extends AuthenticatedWebSession {

	private boolean signedIn = false;

	private Roles roles = new Roles();

	public TestSession(Request request) {
		super(request);
	}

	@Override
	protected boolean authenticate(String username, String password) {
		if (!signedIn) {
			throw new UserNotLoggedInException("");
		}
		return true;
	}

	@Override
	public Roles getRoles() {
		return roles;
	}

	public static TestSession get() {
		return (TestSession) Session.get();
	}

	public void setSignedIn(boolean signedIn) {
		this.signedIn = signedIn;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}
}
