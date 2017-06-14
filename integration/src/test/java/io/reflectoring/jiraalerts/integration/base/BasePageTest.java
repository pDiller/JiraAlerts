package io.reflectoring.jiraalerts.integration.base;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.reflectoring.jiraalerts.integration.homepage.HomePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class BasePageTest {

    private WicketTester wicketTester;

    @Before
    public void setUp() {
        wicketTester = new WicketTester(new MockApplication());
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