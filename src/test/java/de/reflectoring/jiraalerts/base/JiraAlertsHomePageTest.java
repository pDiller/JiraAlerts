package de.reflectoring.jiraalerts.base;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class JiraAlertsHomePageTest {

    private WicketTester wicketTester;

    @Before
    public void setUp() {
        wicketTester = new WicketTester(new MockApplication());
        wicketTester.startPage(JiraAlertsHomePage.class);
    }

    @Test
    public void homepageRendersSuccessfully() {
        wicketTester.assertRenderedPage(JiraAlertsHomePage.class);
    }

    @Test
    public void homepageShowsWelcomeLabel() {
        wicketTester.assertComponent("welcomeLabel", Label.class);
    }
}
