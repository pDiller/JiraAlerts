package io.reflectoring.jiraalerts.dashboard.applicationstate;

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

import io.reflectoring.jiraalerts.common.FeedbackPanelErrorClassModifier;
import io.reflectoring.jiraalerts.common.FormControlLabelPanel;
import io.reflectoring.jiraalerts.common.FormControlPasswordFieldPanel;
import io.reflectoring.jiraalerts.dashboard.RerenderApplicationStateCardEventPayload;

/**
 * Panel for login against existent JIRA-Instance.
 */
public class ConnectApplicationPanel extends GenericPanel<JiraLoginDTO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectApplicationPanel.class);

	@Inject
	private SetupApplicationService setupApplicationService;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param model
	 *            Model with jiraconnection data.
	 */
	public ConnectApplicationPanel(String id, IModel<JiraLoginDTO> model) {
		super(id, model);

		setOutputMarkupId(true);

		Form<JiraLoginDTO> setupForm = new Form<>("setupForm", getModel());

		addGlobalFeedbackPanel();
		addUrlLabel(setupForm);
		addUsernameLabel(setupForm);
		addJiraPasswordComponents(setupForm);

		setupForm.add(new AjaxFallbackButton("submitButton", setupForm) {

			@Override
			protected void onError(Optional<AjaxRequestTarget> targetOptional) {
				super.onError(targetOptional);
				targetOptional.ifPresent(target -> target.add(ConnectApplicationPanel.this));
			}

			@Override
			protected void onSubmit(Optional<AjaxRequestTarget> targetOptional) {
				super.onSubmit(targetOptional);
				setupJiraConnection(ConnectApplicationPanel.this.getModelObject(), targetOptional);
			}
		});

		add(setupForm);
	}

	private void addUrlLabel(Form<JiraLoginDTO> setupForm) {
		IModel<String> urlLabelModel = new ResourceModel("jira.url.label.text");
		IModel<String> urlValueModel = model(from(JiraLoginDTO.class).getUrl()).bind(getModel());
		setupForm.add(new FormControlLabelPanel("urlLabelPanel", urlValueModel, urlLabelModel));
	}

	private void addUsernameLabel(Form<JiraLoginDTO> setupForm) {
		IModel<String> usernameLabelModel = new ResourceModel("jira.username.label.text");
		IModel<String> usernameValueModel = model(from(JiraLoginDTO.class).getUsername()).bind(getModel());
		setupForm.add(new FormControlLabelPanel("usernameLabelPanel", usernameValueModel, usernameLabelModel));
	}

	private void addGlobalFeedbackPanel() {
		FeedbackPanel globalFeedbackPanel = new FencedFeedbackPanel("globalFeedback", this);
		globalFeedbackPanel.add(new FeedbackPanelErrorClassModifier());
		add(globalFeedbackPanel);
	}

	private void addJiraPasswordComponents(Form<JiraLoginDTO> setupForm) {
		IModel<String> jiraPasswordLabelModel = new ResourceModel("setup.password.label.text");
		IModel<String> jiraPasswordModel = model(from(JiraLoginDTO.class).getPassword()).bind(getModel());

		setupForm.add(new FormControlPasswordFieldPanel("passwordInputPanel", jiraPasswordModel, jiraPasswordLabelModel));
	}

	private void setupJiraConnection(JiraLoginDTO jiraLoginDTO, Optional<AjaxRequestTarget> targetOptional) {
		try {
			setupApplicationService.activateApplicaton(jiraLoginDTO);
			targetOptional.ifPresent(target -> send(this, BUBBLE, new RerenderApplicationStateCardEventPayload(target)));
		} catch (SetupApplicationFailedException exception) {
			LOGGER.warn("Reinitialization failed: ", exception);
			error(getString("initialization.application.failed"));
			targetOptional.ifPresent(target -> target.add(ConnectApplicationPanel.this));
		}
	}
}
