package io.reflectoring.jiraalerts.jiracomponent.configuration;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.reflectoring.jiraalerts.jiracomponent.wickettests.JiraComponentTestConfiguration;
import io.reflectoring.jiraalerts.base.wickettests.TestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JiraComponentTestConfiguration.class, TestConfiguration.class })
public class JiraUrlConfigurationPanelTest {

	private static final String NEW_VALUE_TO_SAVE = "http://newValue.com";
	private static final long JIRA_CONNECTION_DATA_ID = 1L;

	@Autowired
	private JiraConnectionConfigurationService jiraConnectionConfigurationServiceMock;

	@Autowired
	private WicketTester wicketTester;

	@Before
	public void setUp() {
		wicketTester.startComponentInPage(JiraUrlConfigurationPanel.class);
	}

	@Test
	public void homepageRendersSuccessfully() {
		wicketTester.assertComponent("", JiraUrlConfigurationPanel.class);
		wicketTester.assertComponent("connectionUrlForm", Form.class);
		wicketTester.assertComponent("connectionUrlForm:connectionInputField", TextField.class);
		wicketTester.assertComponent("connectionUrlForm:submitNewConnectionUrlLink", AjaxSubmitLink.class);
	}

	@Test
	public void formSubmitCallsServicesForSavingNewData() throws Exception {
		FormTester formTester = wicketTester.newFormTester("connectionUrlForm");
		formTester.setValue("connectionInputField", NEW_VALUE_TO_SAVE);

		wicketTester.clickLink("connectionUrlForm:submitNewConnectionUrlLink");

		verify(jiraConnectionConfigurationServiceMock, times(3)).loadConnectionUrl(JIRA_CONNECTION_DATA_ID);
		verify(jiraConnectionConfigurationServiceMock).saveConnectionUrl(JIRA_CONNECTION_DATA_ID, NEW_VALUE_TO_SAVE);
	}
}
