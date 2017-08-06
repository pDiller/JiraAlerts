package io.reflectoring.jiraalerts.jiracomponent.configuration;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.reflectoring.jiraalerts.base.components.LabeledTextfieldInputPanel;
import io.reflectoring.jiraalerts.base.wickettests.TestConfiguration;
import io.reflectoring.jiraalerts.jiracomponent.connection.persistence.JiraConnectionData;
import io.reflectoring.jiraalerts.jiracomponent.wickettests.JiraComponentTestConfiguration;

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
		wicketTester.startComponentInPage(JiraConnectionDataPanel.class);
	}

	@Test
	public void homepageRendersSuccessfully() {
		wicketTester.assertComponent("", JiraConnectionDataPanel.class);
		wicketTester.assertComponent("connectionUrlForm", Form.class);
		wicketTester.assertComponent("connectionUrlForm:connectionUrlPanel", LabeledTextfieldInputPanel.class);
		wicketTester.assertComponent("connectionUrlForm:submitNewConnectionUrlLink", AjaxSubmitLink.class);
	}

	@Test
	public void formSubmitCallsServicesForSavingNewData() throws Exception {
		when(jiraConnectionConfigurationServiceMock.loadJiraConnectionData(anyInt())).thenReturn(new JiraConnectionData());

		FormTester formTester = wicketTester.newFormTester("connectionUrlForm");
		formTester.setValue("connectionUrlPanel:textInputForm:input", NEW_VALUE_TO_SAVE);

		wicketTester.clickLink("connectionUrlForm:submitNewConnectionUrlLink");

		verify(jiraConnectionConfigurationServiceMock, times(3)).loadJiraConnectionData(JIRA_CONNECTION_DATA_ID);
		verify(jiraConnectionConfigurationServiceMock).saveConnectionData(isA(JiraConnectionData.class));
	}
}
