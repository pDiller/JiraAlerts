package io.reflectoring.jiraalerts.integration.admin.firstconfiguration;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.reflectoring.jiraalerts.integration.base.BasePage;
import io.reflectoring.jiraalerts.integration.wickettests.IntegrationTestConfiguration;
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
public class FirstConfigurationPageTest {

    @Autowired
    private WicketTester wicketTester;

    @Before
    public void setUp() {
        wicketTester.startPage(FirstConfigurationPage.class);
    }

    @Test
    public void rendersSuccessfully() throws Exception {
        wicketTester.assertRenderedPage(FirstConfigurationPage.class);
    }
}