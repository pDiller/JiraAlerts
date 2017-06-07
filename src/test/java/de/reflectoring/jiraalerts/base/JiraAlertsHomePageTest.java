package de.reflectoring.jiraalerts.base;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JiraAlertsHomePageTest {

    private WicketTester wicketTester;
    private static final Logger LOG = LoggerFactory.getLogger(JiraAlertsHomePageTest.class);

    @Before
    public void setUp(){
        wicketTester = new WicketTester(new MockApplication());
        wicketTester.startPage(JiraAlertsHomePage.class);
    }

    @Test
    public void homepageRendersSuccessfully(){
        wicketTester.assertRenderedPage(JiraAlertsHomePage.class);
    }

    @Test
    public void homepageShowsWelcomeLabel()   {
        wicketTester.assertComponent("welcomeLabel", Label.class);
    }
}