package io.reflectoring.jiraalerts.integration.homepage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class HomePageTest {

    private WicketTester wicketTester;

    @Before
    public void setUp() {
        wicketTester = new WicketTester(new MockApplication());
        wicketTester.startPage(HomePage.class);
    }

    @Test
    public void homepageRendersSuccessfully() {
        wicketTester.assertRenderedPage(HomePage.class);
    }

    @Test
    public void homepageShowsWelcomeLabel() {
        wicketTester.assertComponent("welcomeLabel", Label.class);
    }
}
