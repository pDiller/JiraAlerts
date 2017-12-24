package io.reflectoring.jiraalerts.applicationstate;

import static java.lang.String.format;
import static org.apache.wicket.event.Broadcast.BUBBLE;
import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

import java.util.Optional;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reflectoring.jiraalerts.application.ApplicationState;
import io.reflectoring.jiraalerts.application.ApplicationStateService;
import io.reflectoring.jiraalerts.common.FeedbackPanelErrorClassModifier;
import io.reflectoring.jiraalerts.common.FormControlPasswordFieldPanel;
import io.reflectoring.jiraalerts.common.FormControlTextFieldPanel;
import io.reflectoring.jiraalerts.dashboard.RerenderApplicationStateCardEventPayload;

/**
 * Panel for initial setup with JIRA.
 */
public class SetupApplicationPanel extends GenericPanel<JiraLoginDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SetupApplicationPanel.class);

	@Inject
	private SetupApplicationService setupApplicationService;

	@Inject
	private ApplicationStateService applicationStateService;

	private FormControlTextFieldPanel<String> urlInputPanel;
	private FormControlTextFieldPanel<String> usernameInputPanel;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param model
	 *            The initial login-values of JIRA.
	 */
	public SetupApplicationPanel(String id, IModel<JiraLoginDTO> model) {
		super(id, model);
		setOutputMarkupId(true);

		Form<JiraLoginDTO> setupForm = new Form<>("setupForm", getModel());

		addGlobalFeedbackPanel();
		addJiraUrlComponents(setupForm);
		addJiraUsernameComponents(setupForm);
		addJiraPasswordComponents(setupForm);

		setupForm.add(new AjaxFallbackButton("submitButton", setupForm) {

			@Override
			protected void onSubmit(Optional<AjaxRequestTarget> targetOptional) {
				super.onSubmit(targetOptional);
				setupJiraConnection(SetupApplicationPanel.this.getModelObject(), targetOptional);
			}
		});

		add(setupForm);
	}

	private void addGlobalFeedbackPanel() {
		FeedbackPanel globalFeedbackPanel = new FencedFeedbackPanel("globalFeedback", this);
		globalFeedbackPanel.add(new FeedbackPanelErrorClassModifier());
		add(globalFeedbackPanel);
	}

	private void addJiraUrlComponents(Form<JiraLoginDTO> setupForm) {
		IModel<String> jiraUrlLabelModel = new ResourceModel("setup.url.label.text");
		IModel<String> jiraUrlModel = model(from(JiraLoginDTO.class).getUrl()).bind(getModel());

		urlInputPanel = new FormControlTextFieldPanel<>("urlInputPanel", jiraUrlModel, jiraUrlLabelModel, true);
		setupForm.add(urlInputPanel);
	}

	private void addJiraUsernameComponents(Form<JiraLoginDTO> setupForm) {
		IModel<String> jiraUsernameLabelModel = new ResourceModel("setup.username.label.text");
		IModel<String> jiraUsernameModel = model(from(JiraLoginDTO.class).getUsername()).bind(getModel());

		usernameInputPanel = new FormControlTextFieldPanel<>("usernameInputPanel", jiraUsernameModel, jiraUsernameLabelModel, true);
		setupForm.add(usernameInputPanel);
	}

	private void addJiraPasswordComponents(Form<JiraLoginDTO> setupForm) {
		IModel<String> jiraPasswordLabelModel = new ResourceModel("setup.password.label.text");
		IModel<String> jiraPasswordModel = model(from(JiraLoginDTO.class).getPassword()).bind(getModel());

		setupForm.add(new FormControlPasswordFieldPanel("passwordInputPanel", jiraPasswordModel, jiraPasswordLabelModel));
	}

	private void setupJiraConnection(JiraLoginDTO jiraLoginDTO, Optional<AjaxRequestTarget> targetOptional) {
		try {
			setupApplicationService.setupApplicaton(jiraLoginDTO);
			targetOptional.ifPresent(target -> send(this, BUBBLE, new RerenderApplicationStateCardEventPayload(target)));
		} catch (SetupApplicationFailedException exception) {
			LOGGER.warn("Setup of application failed: ", exception);
			error(getString("setup.application.failed"));
			targetOptional.ifPresent(target -> target.add(SetupApplicationPanel.this));
		}
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();

		ApplicationState applicationState = applicationStateService.getApplicationState();

		switch (applicationState) {
		case NOT_INITIALIZED:
			// do nothing input is rendered correctly
			break;
		case NOT_ACTIVE:
			urlInputPanel.getInput().setEnabled(false);
			usernameInputPanel.getInput().setEnabled(false);
			break;
		default:
			throw new IllegalStateException(format("This enum value is not supported: '%s'", applicationState));
		}
	}
}
