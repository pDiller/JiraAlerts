package io.reflectoring.jiraalerts.dashboard.device;

import static io.reflectoring.jiraalerts.dashboard.device.DeviceType.RAITO4RPI;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;
import io.reflectoring.jiraalerts.common.FormControlDropDownPanel;
import io.reflectoring.jiraalerts.common.FormControlTextFieldPanel;

@RunWith(MockitoJUnitRunner.class)
public class DevicePanelTest {

	private static final String DEVICE_NAME = "DeviceName";
	private static final DeviceType DEVICE_TYPE = RAITO4RPI;
	private static final String DEVICE_URL = "DeviceUrl";

	private WicketTester wicketTester = new WicketTester(new TestApplication(this));
	private DeviceDTO deviceDTO;

	@Before
	public void setUp() throws Exception {
		deviceDTO = new DeviceDTO();
		wicketTester.startComponentInPage(new DevicePanel("panel", new Model<>(deviceDTO)));
	}

	@Test
	public void rendersSuccessfully() throws Exception {
		wicketTester.assertComponent("panel:deviceForm", Form.class);
		wicketTester.assertComponent("panel:deviceForm:deviceNamePanel", FormControlTextFieldPanel.class);
		wicketTester.assertComponent("panel:deviceForm:deviceTypePanel", FormControlDropDownPanel.class);
		wicketTester.assertComponent("panel:deviceForm:deviceUrlPanel", FormControlTextFieldPanel.class);
	}

	@Test
	public void deviceNameIsRequired() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:deviceForm");

		formTester.select("deviceTypePanel:input", DEVICE_TYPE.getId());
		formTester.setValue("deviceUrlPanel:input", DEVICE_URL);

		formTester.submit();

		wicketTester.assertErrorMessages("input.Required");
	}

	@Test
	public void deviceTypeIsRequired() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:deviceForm");

		formTester.setValue("deviceNamePanel:input", DEVICE_NAME);
		formTester.setValue("deviceUrlPanel:input", DEVICE_URL);

		formTester.submit();

		wicketTester.assertErrorMessages("input.Required");
	}

	@Test
	public void deviceUrlIsRequired() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:deviceForm");

		formTester.setValue("deviceNamePanel:input", DEVICE_NAME);
		formTester.select("deviceTypePanel:input", DEVICE_TYPE.getId());

		formTester.submit();

		wicketTester.assertErrorMessages("input.Required");
	}

	@Test
	public void submitFormSetsValueToDTO() throws Exception {
		FormTester formTester = wicketTester.newFormTester("panel:deviceForm");

		formTester.setValue("deviceNamePanel:input", DEVICE_NAME);
		formTester.select("deviceTypePanel:input", DEVICE_TYPE.getId());
		formTester.setValue("deviceUrlPanel:input", DEVICE_URL);

		formTester.submit();

		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(deviceDTO.getName()).isEqualTo(DEVICE_NAME);
		softAssertions.assertThat(deviceDTO.getType()).isEqualTo(DEVICE_TYPE);
		softAssertions.assertThat(deviceDTO.getUrl()).isEqualTo(DEVICE_URL);
		softAssertions.assertAll();
	}
}
