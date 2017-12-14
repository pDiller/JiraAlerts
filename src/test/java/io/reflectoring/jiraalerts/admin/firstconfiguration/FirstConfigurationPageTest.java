package io.reflectoring.jiraalerts.admin.firstconfiguration;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.admin.applicationactivation.ActivateApplicationPage;
import io.reflectoring.jiraalerts.application.TestApplication;

@RunWith(MockitoJUnitRunner.class)
public class FirstConfigurationPageTest {

	private static final String URL_TO_SAVE = "http://newValue.com";
	private static final String USERNAME_TO_SAVE = "username";

	@Mock
	private JiraConnectionDataService jiraConnectionDataServiceMock;

	private WicketTester wicketTester;

	@Before
	public void setUp() {
		wicketTester = new WicketTester(new TestApplication(this));
		wicketTester.startPage(FirstConfigurationPage.class);
	}

	@Test
	public void rendersSuccessfully() throws Exception {
		wicketTester.assertRenderedPage(FirstConfigurationPage.class);
	}

	@Test
	public void renderComponents() throws Exception {
		wicketTester.assertComponent("firstConfigurationPanel", FirstConfigurationPanel.class);
	}

	@Test
	public void submitFormInPanelRedirectsToHomePage() {
		FormTester formTester = wicketTester.newFormTester("firstConfigurationPanel:jiraConnectionDataForm");
		formTester.setValue("connectionUrlPanel:textInputForm:input", URL_TO_SAVE);
		formTester.setValue("connectionUsernamePanel:textInputForm:input", USERNAME_TO_SAVE);

		wicketTester.clickLink("firstConfigurationPanel:jiraConnectionDataForm:submitNewConnectionUrlLink");

		wicketTester.assertRenderedPage(ActivateApplicationPage.class);
	}
}
