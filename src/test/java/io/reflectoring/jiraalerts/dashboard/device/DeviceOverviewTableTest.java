package io.reflectoring.jiraalerts.dashboard.device;

import org.apache.wicket.markup.Markup;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;

@RunWith(MockitoJUnitRunner.class)
public class DeviceOverviewTableTest {

	private static final long USER_ID = 4711;

	@Mock
	private DeviceDataProvider deviceDataProviderMock;

	private WicketTester wicketTester;

	@Before
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new TestApplication(this));
		wicketTester.startComponentInPage(new DeviceOverviewTable("table", USER_ID), Markup.of("<table wicket:id=table></table>"));
	}

	@Test
	public void rendersSuccessfully() throws Exception {
		wicketTester.assertNoErrorMessage();
	}
}
