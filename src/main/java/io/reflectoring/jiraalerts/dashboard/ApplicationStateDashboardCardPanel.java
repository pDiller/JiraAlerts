package io.reflectoring.jiraalerts.dashboard;

import static java.lang.String.format;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

import io.reflectoring.jiraalerts.appstate.ApplicationState;
import io.reflectoring.jiraalerts.dashboard.applicationstate.*;

/**
 * Dashboard-Card for application state.
 */
public class ApplicationStateDashboardCardPanel extends AbstractDashboardCardPanel<JiraLoginDTO> {

	/**
	 * This constant must be used to replace the current application-state panel.
	 */
	private static final String STATE_COMPONENT_ID = "stateComponent";

	private final IModel<ApplicationState> applicationStateModel;
	private Component stateComponent;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param jiraLoginDTOModel
	 *            Wicket-Model.
	 * @param applicationStateModel
	 *            the model to provide the {@link ApplicationState}.
	 */
	public ApplicationStateDashboardCardPanel(String id, IModel<JiraLoginDTO> jiraLoginDTOModel, IModel<ApplicationState> applicationStateModel) {
		super(id, jiraLoginDTOModel);
		this.applicationStateModel = applicationStateModel;
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
		ApplicationState applicationState = applicationStateModel.getObject();
		switch (applicationState) {
		case NOT_INITIALIZED:
			replaceComponent(new SetupApplicationPanel(STATE_COMPONENT_ID, getModel()), target);
			break;
		case ACTIVE:
			replaceComponent(new LoggedInApplicationPanel(STATE_COMPONENT_ID, getModel()), target);
			break;
		case NOT_ACTIVE:
			replaceComponent(new ConnectApplicationPanel(STATE_COMPONENT_ID, getModel()), target);
			break;
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
