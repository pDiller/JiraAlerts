package io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import io.reflectoring.jiraalerts.base.components.LabeledPasswordInputPanel;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.FirstConfigurationDTO;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public abstract class ActivateApplicationPanel extends GenericPanel<String> {

    @SpringBean
    private ActivateApplicationService activateApplicationService;

    public ActivateApplicationPanel(String id, IModel<String> activationPasswordModel) {
        super(id, activationPasswordModel);

        BootstrapForm activateApplicationForm = new BootstrapForm("activateApplicationForm");
        activateApplicationForm.add(new LabeledPasswordInputPanel("activationPasswordPanel", new StringResourceModel("activationPassword.label", this), getModel()));
        activateApplicationForm.add(new AjaxSubmitLink("activateApplicationLink", activateApplicationForm) {

            @Override
            protected void onError(AjaxRequestTarget target) {
                if (target != null) {
                    target.add(activateApplicationForm);
                }
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                activateApplicationService.activateApplication();
                activateApplication(target);
            }
        });
        add(activateApplicationForm);
    }

    protected abstract void activateApplication(AjaxRequestTarget target);
}
