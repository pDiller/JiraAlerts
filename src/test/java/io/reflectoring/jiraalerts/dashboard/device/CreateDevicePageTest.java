package io.reflectoring.jiraalerts.dashboard.device;

import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.mock.MockHomePage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;
import io.reflectoring.jiraalerts.application.testsetup.TestSession;

@RunWith(MockitoJUnitRunner.class)
public class CreateDevicePageTest {

	private WicketTester wicketTester;

	@Mock
	private DeviceService deviceServiceMock;

	@Before
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new TestApplication(this));
		TestSession.get().setRoles(new Roles("administrator"));
		wicketTester.startPage(CreateDevicePage.class);
	}

	@Test
	public void rendersSuccessfully() throws Exception {
		wicketTester.assertRenderedPage(CreateDevicePage.class);
		wicketTester.assertComponent("createDeviceForm", Form.class);
		wicketTester.assertComponent("createDeviceForm:devicePanel", DevicePanel.class);
		wicketTester.assertComponent("createDeviceForm:testUrlButton", AjaxFallbackButton.class);
		wicketTester.assertComponent("createDeviceForm:submitButton", AjaxFallbackButton.class);
	}

	@Test
	public void redirectsToLoginPageWhenRoleIsNotCorrect() throws Exception {
		TestSession.get().setRoles(new Roles("noRole"));
		wicketTester.startPage(CreateDevicePage.class);

		wicketTester.assertRenderedPage(MockHomePage.class);
	}
}
