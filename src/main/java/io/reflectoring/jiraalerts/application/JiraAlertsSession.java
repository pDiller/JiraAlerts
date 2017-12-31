package io.reflectoring.jiraalerts.application;

import static java.lang.String.format;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;

import io.reflectoring.jiraalerts.application.login.HashService;
import io.reflectoring.jiraalerts.application.login.UserDTO;
import io.reflectoring.jiraalerts.application.login.UserNotLoggedInException;
import io.reflectoring.jiraalerts.application.login.UserService;

/**
 * The Session for JiraAlerts.
 */
public class JiraAlertsSession extends AuthenticatedWebSession {

	@Inject
	private HashService hashService;

	@Inject
	private UserService userService;

	private long userId;

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
			this.userId = loadedUser.getId();
			return true;
		} else {
			throw new UserNotLoggedInException(format("Password invalid for username '%s'", loadedUser.getUsername()));
		}
	}

	@Override
	public Roles getRoles() {
		return new Roles("administrator");
	}

	/**
	 * Should be used when special methods of {@link JiraAlertsSession} are used. Otherwise use {@link AuthenticatedWebSession#get()} or
	 * {@link Session#get()}.
	 *
	 * @return the casted {@link JiraAlertsSession}.
	 */
	public static JiraAlertsSession get() {
		return ((JiraAlertsSession) Session.get());
	}

	/**
	 * @return The Id of the user which is signed in.
	 */
	public long getUserId() {
		return userId;
	}
}
