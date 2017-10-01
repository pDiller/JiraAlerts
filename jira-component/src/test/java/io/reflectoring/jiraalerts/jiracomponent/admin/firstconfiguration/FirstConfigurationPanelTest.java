package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

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

import io.reflectoring.jiraalerts.base.components.LabeledTextfieldInputPanel;
import io.reflectoring.jiraalerts.jiracomponent.wickettests.JiraComponentTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JiraComponentTestConfiguration.class)
public class FirstConfigurationPanelTest {

	private static final String URL_TO_SAVE = "http://newValue.com";
	private static final String USERNAME_TO_SAVE = "username";

	@Autowired
	private WicketTester wicketTester;

	@Autowired
	private JiraConnectionDataService jiraConnectionDataServiceMock;

	private boolean jiraConnectionSaved = false;

	@Before
	public void setUp() {
		JiraConnectionDataDTO jiraConnectionDataDTO = new JiraConnectionDataDTO();
		IModel<JiraConnectionDataDTO> jiraConnectionDataDTOModel = new Model<>(jiraConnectionDataDTO);
		wicketTester.startComponentInPage(new FirstConfigurationPanel("panel", jiraConnectionDataDTOModel) {

			@Override
			protected void onJiraConnectionSaved(AjaxRequestTarget target) {
				jiraConnectionSaved = true;
			}
		});

	}

	@Test
	public void homepageRendersSuccessfully() {
		wicketTester.assertComponent("panel", FirstConfigurationPanel.class);
		wicketTester.assertComponent("panel:jiraConnectionDataForm", BootstrapForm.class);
		wicketTester.assertComponent("panel:jiraConnectionDataForm:connectionUrlPanel", LabeledTextfieldInputPanel.class);
		wicketTester.assertComponent("panel:jiraConnectionDataForm:connectionUsernamePanel", LabeledTextfieldInputPanel.class);
		wicketTester.assertComponent("panel:jiraConnectionDataForm:submitNewConnectionUrlLink", AjaxSubmitLink.class);
	}

	@Test
	public void formSubmitCallsServicesForSavingNewData() {
		FormTester formTester = wicketTester.newFormTester("panel:jiraConnectionDataForm");
		formTester.setValue("connectionUrlPanel:textInputForm:input", URL_TO_SAVE);
		formTester.setValue("connectionUsernamePanel:textInputForm:input", USERNAME_TO_SAVE);

		wicketTester.clickLink("panel:jiraConnectionDataForm:submitNewConnectionUrlLink");

		verify(jiraConnectionDataServiceMock).saveJiraConnectionData(any(JiraConnectionDataDTO.class));
	}
}
