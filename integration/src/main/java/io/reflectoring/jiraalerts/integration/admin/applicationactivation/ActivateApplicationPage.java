package io.reflectoring.jiraalerts.integration.admin.applicationactivation;

import io.reflectoring.jiraalerts.integration.Login.LoginPage;
import io.reflectoring.jiraalerts.integration.base.BasePage;
import io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation.ActivateApplicationPanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class ActivateApplicationPage extends BasePage {

    public ActivateApplicationPage() {
        IModel<String> activateApplicationPassword = new Model<>("");
        add(new ActivateApplicationPanel("activateApplicationPanel", activateApplicationPassword) {
            @Override
            protected void activateApplication(AjaxRequestTarget target) {
                setResponsePage(LoginPage.class);
            }
        });
    }
}
