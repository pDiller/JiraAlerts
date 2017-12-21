package io.reflectoring.jiraalerts.application;

import static java.lang.String.format;

import javax.inject.Inject;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;

import io.reflectoring.jiraalerts.login.HashService;
import io.reflectoring.jiraalerts.login.UserDTO;
import io.reflectoring.jiraalerts.login.UserNotLoggedInException;
import io.reflectoring.jiraalerts.login.UserService;

public class JiraAlertsSession extends AuthenticatedWebSession {

	@Inject
	private HashService hashService;

	@Inject
	private UserService userService;

	public JiraAlertsSession(Request request) {
		super(request);
		Injector.get().inject(this);
	}

	@Override
	protected boolean authenticate(String username, String password) {
		UserDTO loadedUser = userService.findUserByUsername(username);
		if (loadedUser != null) {
			return checkPassword(password, loadedUser);
		} else {
			throw new UserNotLoggedInException(format("No user found in database for username '%s'", username));
		}
	}

	private boolean checkPassword(String password, UserDTO loadedUser) {
		String hashedPassword = hashService.hashPassword(password, loadedUser.getSalt());
		if (loadedUser.getPassword().equals(hashedPassword)) {
			return true;
		} else {
			throw new UserNotLoggedInException(format("Password invalid for username '%s'", loadedUser.getUsername()));
		}
	}

	@Override
	public Roles getRoles() {
		return new Roles("administrator");
	}
}
