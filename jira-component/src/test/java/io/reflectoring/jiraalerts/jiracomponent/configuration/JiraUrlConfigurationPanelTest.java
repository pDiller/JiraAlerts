package io.reflectoring.jiraalerts.jiracomponent.configuration;

import io.reflectoring.jiraalerts.jiracomponent.wickettests.JiraComponentTestConfiguration;
import io.reflectoring.jiraalerts.shared.wickettests.TestConfiguration;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JiraComponentTestConfiguration.class, TestConfiguration.class})
public class JiraUrlConfigurationPanelTest {

    @Autowired
    private WicketTester wicketTester;

    @Before
    public void setUp() {
        wicketTester.startComponentInPage(JiraUrlConfigurationPanel.class);
    }

    @Test
    public void homepageRendersSuccessfully() {
        wicketTester.assertComponent("", JiraUrlConfigurationPanel.class);
    }
}
