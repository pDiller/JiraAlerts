package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.reflectoring.jiraalerts.jiracomponent.wickettests.JiraComponentTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JiraComponentTestConfiguration.class)
public class FirstConfigurationPanelTest {

	@Autowired
	private WicketTester wicketTester;

	@Before
	public void setUp() {
		JiraConnectionDataDTO jiraConnectionDataDTO = new JiraConnectionDataDTO();
		IModel<JiraConnectionDataDTO> jiraConnectionDataDTOModel = new Model<>(jiraConnectionDataDTO);
		wicketTester.startComponentInPage(new FirstConfigurationPanel("panel", jiraConnectionDataDTOModel));
	}

	@Test
	public void homepageRendersSuccessfully() {
		wicketTester.assertComponent("panel", FirstConfigurationPanel.class);
	}
}
