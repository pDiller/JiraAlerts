package io.reflectoring.jiraalerts.jiracomponent.configuration;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.UrlValidator;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

import io.reflectoring.jiraalerts.base.components.LabeledTextfieldInputPanel;

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

		Form<Void> connectionUrlForm = new BootstrapForm<>("connectionUrlForm");

		IModel<String> connectionUrlLabelModel = new StringResourceModel("connectionUrl.label");

		IValidator<String> urlValidator = new UrlValidator(URL_SCHEMES);
		connectionUrlForm.add(new LabeledTextfieldInputPanel("connectionUrlPanel", connectionUrlLabelModel, getModel(), urlValidator));

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
