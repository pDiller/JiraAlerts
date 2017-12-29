package io.reflectoring.jiraalerts.application.state;

import javax.inject.Inject;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;

/**
 * Model to provide the applicationstate
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
