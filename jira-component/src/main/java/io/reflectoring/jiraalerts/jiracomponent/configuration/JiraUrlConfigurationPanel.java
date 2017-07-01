package io.reflectoring.jiraalerts.jiracomponent.configuration;

import static org.apache.wicket.validation.validator.UrlValidator.ALLOW_2_SLASHES;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.UrlValidator;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

/** Panel to configure the URL of JIRA instance. */
public class JiraUrlConfigurationPanel extends GenericPanel<String> {

	/**
	 * https://github.com/pDiller/JiraAlerts/issues/13 First implementation allows just one configuration. Servicelayer is implemented to allow more.
	 */
	private static final long JIRA_CONNECTION_DATA_ID = 1L;
	private static final String[] URL_SCHEMES = { "https", "http" };

	@SpringBean
	private JiraConnectionConfigurationService jiraConnectionConfigurationService;

	public JiraUrlConfigurationPanel(String id) {
		super(id, new LoadJiraConnectionUrlModel(Model.of(JIRA_CONNECTION_DATA_ID)));

		TextField<String> connectionValueTextField = new TextField<>("connectionInputField", getModel());
		connectionValueTextField.add(new UrlValidator(URL_SCHEMES, ALLOW_2_SLASHES));
		connectionValueTextField.setOutputMarkupId(true);

		FeedbackPanel connectionInputFieldFeedback = new ComponentFeedbackPanel("connectionInputFieldFeedback", connectionValueTextField);
		connectionInputFieldFeedback.setOutputMarkupId(true);

		connectionValueTextField.add(new AjaxFormComponentUpdatingBehavior("change") {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if (target != null) {
					target.add(connectionInputFieldFeedback, connectionValueTextField);
				}
			}

			@Override
			protected void onError(AjaxRequestTarget target, RuntimeException e) {
				if (target != null) {
					target.add(connectionInputFieldFeedback, connectionValueTextField);
				}
			}
		});

		Form<Void> connectionUrlForm = new BootstrapForm<Void>("connectionUrlForm") {

			@Override
			protected void onError() {
				super.onError();
			}
		};
		connectionUrlForm.add(connectionValueTextField);
		connectionUrlForm.add(connectionInputFieldFeedback);
		connectionUrlForm.add(new AjaxSubmitLink("submitNewConnectionUrlLink", connectionUrlForm) {

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				String newConnectionUrl = getModelObject();
				jiraConnectionConfigurationService.saveConnectionUrl(JIRA_CONNECTION_DATA_ID, newConnectionUrl);
				if (target != null) {
					target.add(connectionUrlForm);
				}
			}
		});

		add(connectionUrlForm);
	}
}
