package io.reflectoring.jiraalerts.integration.admin.firstconfiguration;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.reflectoring.jiraalerts.integration.homepage.HomePage;
import io.reflectoring.jiraalerts.integration.wickettests.IntegrationTestConfiguration;
import io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration.FirstConfigurationPanel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class FirstConfigurationPageTest {

	private static final String URL_TO_SAVE = "http://newValue.com";
	private static final String USERNAME_TO_SAVE = "username";

	@Autowired
	private WicketTester wicketTester;

	@Before
	public void setUp() {
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

		wicketTester.assertRenderedPage(HomePage.class);
	}
}
