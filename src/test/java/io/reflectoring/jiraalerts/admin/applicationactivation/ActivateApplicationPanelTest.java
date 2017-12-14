package io.reflectoring.jiraalerts.admin.applicationactivation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

import io.reflectoring.jiraalerts.application.TestApplication;
import io.reflectoring.jiraalerts.base.LabeledPasswordInputPanel;

@RunWith(MockitoJUnitRunner.class)
public class ActivateApplicationPanelTest {

	private static final String ACTIVATION_PASSWORD = "new Password";

	private WicketTester wicketTester;

	@Mock
	private ActivateApplicationService activateApplicationServiceMock;

	private boolean applicationActivated = false;

	@Before
	public void setUp() {
		wicketTester = new WicketTester(new TestApplication(this));
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
		wicketTester.assertComponent("panel:activateApplicationForm:feedback", ComponentFeedbackPanel.class);
	}

	@Test
	public void formSubmitCallsServicesForActivateApplication() {
		FormTester formTester = wicketTester.newFormTester("panel:activateApplicationForm");
		formTester.setValue("activationPasswordPanel:textInputForm:password", ACTIVATION_PASSWORD);

		wicketTester.clickLink("panel:activateApplicationForm:activateApplicationLink");

		verify(activateApplicationServiceMock, atLeastOnce()).activateApplication(ACTIVATION_PASSWORD);
	}

	@Test
	public void formSubmitActivatesApplicationWithCorrectPassword() {
		when(activateApplicationServiceMock.activateApplication(ACTIVATION_PASSWORD)).thenReturn(true);
		FormTester formTester = wicketTester.newFormTester("panel:activateApplicationForm");
		formTester.setValue("activationPasswordPanel:textInputForm:password", ACTIVATION_PASSWORD);

		wicketTester.clickLink("panel:activateApplicationForm:activateApplicationLink");

		assertThat(applicationActivated).isTrue();
	}

	@Test
	public void formSubmitDoesNotActivateApplicationWithCorrectPassword() {
		FormTester formTester = wicketTester.newFormTester("panel:activateApplicationForm");
		formTester.setValue("activationPasswordPanel:textInputForm:password", ACTIVATION_PASSWORD);

		wicketTester.clickLink("panel:activateApplicationForm:activateApplicationLink");

		assertThat(applicationActivated).isFalse();
	}
}
