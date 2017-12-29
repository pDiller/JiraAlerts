package io.reflectoring.jiraalerts.dashboard.applicationstate;

import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import io.reflectoring.jiraalerts.common.FormControlLabelPanel;

public class LoggedInApplicationPanelTest {

	private static final String TEST_USERNAME = "username";
	private static final String TEST_URL = "url";

	private WicketTester wicketTester = new WicketTester();

	@Before
	public void setUp() throws Exception {
		JiraLoginDTO jiraLoginDTO = new JiraLoginDTO();
		jiraLoginDTO.setUsername(TEST_USERNAME);
		jiraLoginDTO.setUrl(TEST_URL);
		wicketTester.startComponentInPage(new LoggedInApplicationPanel("panel", Model.of(jiraLoginDTO)));
	}

	@Test
	public void rendersSuccessfull() throws Exception {
		wicketTester.assertComponent("panel:urlLabelPanel", FormControlLabelPanel.class);
		wicketTester.assertComponent("panel:usernameLabelPanel", FormControlLabelPanel.class);
	}

	@Test
	public void usernameIsBoundToLabelPanel() throws Exception {
		wicketTester.assertModelValue("panel:usernameLabelPanel:input", TEST_USERNAME);
	}

	@Test
	public void urlIsBoundToLabelPanel() throws Exception {
		wicketTester.assertModelValue("panel:urlLabelPanel:input", TEST_URL);
	}
}
