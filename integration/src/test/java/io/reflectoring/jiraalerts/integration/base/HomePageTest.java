package io.reflectoring.jiraalerts.integration.base;


import io.reflectoring.jiraalerts.integration.homepage.HomePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HomePageTest {

    @Autowired
    private WicketTester wicketTester;

    @Before
    public void setUp() {
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
