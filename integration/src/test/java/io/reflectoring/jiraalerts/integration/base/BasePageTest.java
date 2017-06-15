package io.reflectoring.jiraalerts.integration.base;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.reflectoring.jiraalerts.integration.homepage.HomePage;
import io.reflectoring.jiraalerts.integration.wickettests.IntegrationTestConfiguration;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class BasePageTest {

    @Autowired
    private WicketTester wicketTester;

    @Before
    public void setUp() {
        wicketTester.startPage(BasePage.class);
    }

    @Test
    public void basepageRendersSuccessfully() {
        wicketTester.assertRenderedPage(BasePage.class);
    }

    @Test
    public void basepageContainsNavbar() {
        wicketTester.assertComponent("navbarHeaderPanel", NavbarHeaderPanel.class);
    }
}