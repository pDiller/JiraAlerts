package io.reflectoring.jiraalerts.base;

import io.reflectoring.jiraalerts.shared.wickettests.JiraAlertsTestConfiguration;
import io.reflectoring.jiraalerts.shared.wickettests.TestConfiguration;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JiraAlertsTestConfiguration.class, TestConfiguration.class})
public class JiraAlertsHomePageTest {

    @Autowired
    private WicketTester wicketTester;

    @Before
    public void setUp() {
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
