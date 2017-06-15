package io.reflectoring.jiraalerts.integration.homepage;

import org.apache.wicket.markup.html.basic.Label;
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
