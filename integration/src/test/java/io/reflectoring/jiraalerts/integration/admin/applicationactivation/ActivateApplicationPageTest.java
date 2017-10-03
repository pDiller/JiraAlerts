package io.reflectoring.jiraalerts.integration.admin.applicationactivation;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.reflectoring.jiraalerts.jiracomponent.admin.applicationactivation.ActivateApplicationPanel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.reflectoring.jiraalerts.integration.wickettests.IntegrationTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class ActivateApplicationPageTest {

	@Autowired
	private WicketTester wicketTester;

	@Before
	public void setUp() {
		wicketTester.startPage(ActivateApplicationPage.class);
	}

	@Test
	public void rendersSuccessfully() throws Exception {
		wicketTester.assertRenderedPage(ActivateApplicationPage.class);
		wicketTester.assertComponent("activateApplicationPanel", ActivateApplicationPanel.class);
	}
}
