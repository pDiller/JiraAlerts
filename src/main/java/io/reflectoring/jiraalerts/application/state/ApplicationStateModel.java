package io.reflectoring.jiraalerts.application.state;

import javax.inject.Inject;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;

import io.reflectoring.jiraalerts.appstate.ApplicationState;
import io.reflectoring.jiraalerts.appstate.ApplicationStateService;

/**
 * Model to provide the state of the application
 */
public class ApplicationStateModel implements IModel<ApplicationState> {

	@Inject
	private ApplicationStateService applicationStateService;

	/**
	 * Constructor.
	 */
	public ApplicationStateModel() {
		Injector.get().inject(this);
	}

	@Override
	public ApplicationState getObject() {
		return applicationStateService.getApplicationState();
	}
}
