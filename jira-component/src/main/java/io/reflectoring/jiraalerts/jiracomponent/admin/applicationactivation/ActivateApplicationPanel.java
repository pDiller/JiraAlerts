package io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

import io.reflectoring.jiraalerts.base.components.LabeledPasswordInputPanel;

public abstract class ActivateApplicationPanel extends GenericPanel<String> {

	@SpringBean
	private ActivateApplicationService activateApplicationService;

	public ActivateApplicationPanel(String id, IModel<String> activationPasswordModel) {
		super(id, activationPasswordModel);

		setOutputMarkupId(true);

		BootstrapForm activateApplicationForm = new BootstrapForm("activateApplicationForm");
		activateApplicationForm
		        .add(new LabeledPasswordInputPanel("activationPasswordPanel", new StringResourceModel("activationPassword.label", this), getModel()));
		activateApplicationForm.add(new AjaxSubmitLink("activateApplicationLink", activateApplicationForm) {

			@Override
			protected void onError(AjaxRequestTarget target) {
				if (target != null) {
					target.add(activateApplicationForm);
				}
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				boolean applicationActivationSuccessfull = activateApplicationService
				        .activateApplication(ActivateApplicationPanel.this.getModelObject());
				if (applicationActivationSuccessfull) {
					activateApplication(target);
				} else {
					activateApplicationForm.error(ActivateApplicationPanel.this.getString("activation.failed.text"));
					target.add(ActivateApplicationPanel.this);
				}
			}
		});
		activateApplicationForm.add(new ComponentFeedbackPanel("feedback", activateApplicationForm));
		add(activateApplicationForm);
	}

	protected abstract void activateApplication(AjaxRequestTarget target);
}
