package io.reflectoring.jiraalerts.jiracomponent.configuration;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.UrlValidator;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

import io.reflectoring.jiraalerts.base.components.LabeledPasswordInputPanel;
import io.reflectoring.jiraalerts.base.components.LabeledTextfieldInputPanel;
import io.reflectoring.jiraalerts.jiracomponent.configuration.persistence.JiraConnectionData;

/** Panel to configure the URL of JIRA instance. */
public class JiraConnectionDataPanel extends GenericPanel<JiraConnectionDataDTO> {

	/**
	 * https://github.com/pDiller/JiraAlerts/issues/13 First implementation allows just one configuration. Servicelayer is implemented to allow more.
	 */
	private static final long JIRA_CONNECTION_DATA_ID = 1L;
	private static final String[] URL_SCHEMES = { "https", "http" };
	private static final String CSS_CLASS_SUCCESS = "alert alert-success";

	@SpringBean
	private JiraConnectionConfigurationService jiraConnectionConfigurationService;

	public JiraConnectionDataPanel(String id) {
		super(id, new LoadJiraConnectionUrlModel(Model.of(JIRA_CONNECTION_DATA_ID)));

		BootstrapForm<Void> connectionUrlForm = new BootstrapForm<>("connectionUrlForm");

		IModel<String> connectionUrlLabelModel = new StringResourceModel("connectionUrl.label");
		IValidator<String> urlValidator = new UrlValidator(URL_SCHEMES);
		IModel<String> connectionUrlModel = model(from(JiraConnectionData.class).getUrl()).bind(getModel());
		connectionUrlForm.add(new LabeledTextfieldInputPanel("connectionUrlPanel", connectionUrlLabelModel, connectionUrlModel, urlValidator));

		IModel<String> connectionUsernameLabelModel = new StringResourceModel("connectionUsername.label");
		IModel<String> connectionUsernameModel = model(from(JiraConnectionData.class).getUsername()).bind(getModel());
		connectionUrlForm.add(new LabeledTextfieldInputPanel("connectionUsernamePanel", connectionUsernameLabelModel, connectionUsernameModel));

		IModel<String> connectionPasswordLabelModel = new StringResourceModel("connectionPassword.label");
		IModel<String> connectionPasswordModel = model(from(JiraConnectionData.class).getPw()).bind(getModel());
		connectionUrlForm.add(new LabeledPasswordInputPanel("connectionPasswordPanel", connectionPasswordLabelModel, connectionPasswordModel));

		connectionUrlForm.add(new FencedFeedbackPanel("feedback", connectionUrlForm) {

			@Override
			protected String getCSSClass(FeedbackMessage message) {
				return CSS_CLASS_SUCCESS;
			}
		});
		connectionUrlForm.add(new AjaxSubmitLink("submitNewConnectionUrlLink", connectionUrlForm) {

			@Override
			protected void onError(AjaxRequestTarget target) {
				if (target != null) {
					target.add(connectionUrlForm);
				}
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				JiraConnectionDataDTO newConnectionData = JiraConnectionDataPanel.this.getModelObject();
				jiraConnectionConfigurationService.saveConnectionData(newConnectionData);
				if (target != null) {
					success(getString("jiraconfig.saved"));
					target.add(connectionUrlForm);
				}
			}
		});

		add(connectionUrlForm);
	}
}
