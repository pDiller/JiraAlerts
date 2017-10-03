package io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

import io.reflectoring.jiraalerts.base.components.LabeledPasswordInputPanel;
import io.reflectoring.jiraalerts.jiracomponent.wickettests.JiraComponentTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JiraComponentTestConfiguration.class)
public class ActivateApplicationPanelTest {

	private static final String ACTIVATION_PASSWORD = "new Password";

	@Autowired
	private WicketTester wicketTester;

	@Autowired
	private ActivateApplicationService activateApplicationService;

	private boolean applicationActivated = false;

	@Before
	public void setUp() {
		IModel<String> activationPasswordModel = new Model<>("");
		wicketTester.startComponentInPage(new ActivateApplicationPanel("panel", activationPasswordModel) {

			@Override
			protected void activateApplication(AjaxRequestTarget target) {
				applicationActivated = true;
			}
		});

	}

	@Test
	public void panelRendersSuccessfully() {
		wicketTester.assertComponent("panel", ActivateApplicationPanel.class);
		wicketTester.assertComponent("panel:activateApplicationForm", BootstrapForm.class);
		wicketTester.assertComponent("panel:activateApplicationForm:activationPasswordPanel", LabeledPasswordInputPanel.class);
		wicketTester.assertComponent("panel:activateApplicationForm:activateApplicationLink", AjaxSubmitLink.class);
	}

	@Test
	public void formSubmitCallsServicesForActivateApplication() {
		FormTester formTester = wicketTester.newFormTester("panel:activateApplicationForm");
		formTester.setValue("activationPasswordPanel:textInputForm:password", ACTIVATION_PASSWORD);

		wicketTester.clickLink("panel:activateApplicationForm:activateApplicationLink");

		verify(activateApplicationService).activateApplication();
		assertThat(applicationActivated).isTrue();
	}
}
