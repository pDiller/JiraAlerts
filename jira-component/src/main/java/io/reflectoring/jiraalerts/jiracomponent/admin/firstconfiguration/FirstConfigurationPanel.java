package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.UrlValidator;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

import io.reflectoring.jiraalerts.base.components.LabeledTextfieldInputPanel;

public class FirstConfigurationPanel extends GenericPanel<JiraConnectionDataDTO> {

	private static final String[] URL_SCHEMES = { "https", "http" };

	public FirstConfigurationPanel(String id, IModel<JiraConnectionDataDTO> jiraConnectionDataDTOModel) {
		super(id, jiraConnectionDataDTOModel);

		BootstrapForm<Void> jiraConnectionDataForm = new BootstrapForm<>("jiraConnectionDataForm");

		IModel<String> connectionUrlLabelModel = new StringResourceModel("connectionUrl.label", this);
		IValidator<String> urlValidator = new UrlValidator(URL_SCHEMES);
		IModel<String> connectionUrlModel = model(from(JiraConnectionDataDTO.class).getUrl()).bind(getModel());
		jiraConnectionDataForm.add(new LabeledTextfieldInputPanel("connectionUrlPanel", connectionUrlLabelModel, connectionUrlModel, urlValidator));

		IModel<String> connectionUsernameLabelModel = new StringResourceModel("connectionUsername.label", this);
		IModel<String> connectionUsernameModel = model(from(JiraConnectionDataDTO.class).getUsername()).bind(getModel());
		jiraConnectionDataForm.add(new LabeledTextfieldInputPanel("connectionUsernamePanel", connectionUsernameLabelModel, connectionUsernameModel));

		jiraConnectionDataForm.add(new AjaxSubmitLink("submitNewConnectionUrlLink", jiraConnectionDataForm) {

			@Override
			protected void onError(AjaxRequestTarget target) {
				if (target != null) {
					target.add(jiraConnectionDataForm);
				}
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				// TODO
			}
		});

		add(jiraConnectionDataForm);
	}
}
