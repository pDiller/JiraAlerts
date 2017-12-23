package io.reflectoring.jiraalerts.dashboard;

import static java.lang.String.format;

import javax.inject.Inject;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

import io.reflectoring.jiraalerts.application.ApplicationState;
import io.reflectoring.jiraalerts.application.ApplicationStateService;
import io.reflectoring.jiraalerts.applicationstate.JiraLoginDTO;
import io.reflectoring.jiraalerts.applicationstate.LoggedInApplicationPanel;
import io.reflectoring.jiraalerts.applicationstate.SetupApplicationPanel;

/**
 * Dashboard-Card for application state.
 */
public class ApplicationStateDashboardCardPanel extends AbstractDashboardCardPanel<JiraLoginDTO> {

	/**
	 * This constant must be used to replace the actual application-state panel.
	 */
	private static final String STATE_COMPONENT_ID = "setupApplicationPanel";

	@Inject
	private ApplicationStateService applicationStateService;

	private Component stateComponent;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param jiraLoginDTOModel
	 *            Wicket-Model.
	 */
	public ApplicationStateDashboardCardPanel(String id, IModel<JiraLoginDTO> jiraLoginDTOModel) {
		super(id, jiraLoginDTOModel);
		setOutputMarkupId(true);

		stateComponent = new WebMarkupContainer(STATE_COMPONENT_ID, getModel());

		add(stateComponent);
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		setApplicationStateComponent(null);
	}

	@Override
	public void onEvent(IEvent<?> event) {
		super.onEvent(event);

		Object payload = event.getPayload();
		if (payload instanceof RerenderApplicationStateCardEventPayload) {
			setApplicationStateComponent(((RerenderApplicationStateCardEventPayload) payload).getTarget());
		}
	}

	private void setApplicationStateComponent(AjaxRequestTarget target) {
		ApplicationState applicationState = applicationStateService.getApplicationState();
		switch (applicationState) {
		case NOT_INITIALIZED:
			replaceComponent(new SetupApplicationPanel(STATE_COMPONENT_ID, getModel()), target);
			break;
		case ACTIVE:
			replaceComponent(new LoggedInApplicationPanel(STATE_COMPONENT_ID, getModel()), target);
			break;
		case NOT_ACTIVE:
			throw new NotImplementedException("This part is not implemented yet");
		default:
			throw new IllegalStateException(format("This enum value is not supported: '%s'", applicationState));
		}
	}

	private void replaceComponent(Component componentForReplacement, AjaxRequestTarget target) {
		stateComponent.replaceWith(componentForReplacement);
		stateComponent = componentForReplacement;

		if (target != null) {
			target.add(ApplicationStateDashboardCardPanel.this);
		}
	}
}
