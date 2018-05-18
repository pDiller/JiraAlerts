package io.reflectoring.jiraalerts.application.testsetup;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import io.reflectoring.jiraalerts.application.JiraAlertsSession;
import io.reflectoring.jiraalerts.application.login.UserNotLoggedInException;

public class TestSession extends JiraAlertsSession {

	private boolean signedIn = false;
	private long userId;

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
		return (TestSession) JiraAlertsSession.get();
	}

	public void setSignedIn(boolean signedIn) {
		this.signedIn = signedIn;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
