package io.reflectoring.jiraalerts.integration.homepage;

import io.reflectoring.jiraalerts.integration.admin.ApplicationStatusService;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.model.ResourceModel;

import io.reflectoring.jiraalerts.integration.base.BasePage;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Welcomepage for JiraAlerts.
 */
public class HomePage extends BasePage {

	@SpringBean
	private ApplicationStatusService applicationStatusService;

	private WebMarkupContainer applicationDeactivatedContainer;

	/**
	 * Constructor for HomePage.
	 */
	public HomePage() {
		add(new Label("welcomeLabel", new ResourceModel("welcome.text")));

		applicationDeactivatedContainer = new WebMarkupContainer("applicationDeactivatedContainer");
		MultiLineLabel applicationDeactivatedLabel = new MultiLineLabel("applicationDeactivatedLabel", new StringResourceModel("application.deactivated", HomePage.this));
		applicationDeactivatedContainer.add(applicationDeactivatedLabel);
		add(applicationDeactivatedContainer);
	}

	@Override
	protected void onConfigure() {
		applicationDeactivatedContainer.setVisible(!applicationStatusService.isApplicationActivated());
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		applicationDeactivatedContainer.detach();
	}
}
