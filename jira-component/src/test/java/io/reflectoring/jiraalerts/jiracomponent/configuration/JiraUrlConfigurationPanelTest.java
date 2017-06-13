package io.reflectoring.jiraalerts.jiracomponent.configuration;

import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class JiraUrlConfigurationPanelTest {

    private WicketTester wicketTester;

    @Before
    public void setUp() {
        wicketTester = new WicketTester(new MockApplication());
        wicketTester.startComponentInPage(JiraUrlConfigurationPanel.class);
    }

    @Test
    public void homepageRendersSuccessfully() {
        wicketTester.assertComponent("", JiraUrlConfigurationPanel.class);
    }
}
