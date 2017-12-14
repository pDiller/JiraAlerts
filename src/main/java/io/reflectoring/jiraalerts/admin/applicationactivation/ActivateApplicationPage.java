package io.reflectoring.jiraalerts.admin.applicationactivation;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import io.reflectoring.jiraalerts.base.BasePage;
import io.reflectoring.jiraalerts.login.LoginPage;

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
