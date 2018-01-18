package io.reflectoring.jiraalerts.dashboard.device;

import static java.util.Arrays.asList;
import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.util.ListModel;

import io.reflectoring.jiraalerts.common.FormControlDropDownPanel;
import io.reflectoring.jiraalerts.common.FormControlTextFieldPanel;

/**
 * Panel, which shows the inputs for create and edit an {@link Device}.
 */
public class DevicePanel extends GenericPanel<DeviceDTO> {

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-ID.
	 * @param model
	 *            the {@link DeviceDTO}.
	 */
	public DevicePanel(String id, IModel<DeviceDTO> model) {
		super(id, model);
		Form<DeviceDTO> deviceForm = new Form<>("deviceForm", getModel());
		addDeviceNameComponent(deviceForm);
		addDeviceTypeComponent(deviceForm);
		addDeviceUrlComponent(deviceForm);
		add(deviceForm);
	}

	private void addDeviceNameComponent(Form<DeviceDTO> deviceForm) {
		IModel<String> deviceNameLabelModel = new ResourceModel("device.name.text");
		IModel<String> deviceNameModel = model(from(DeviceDTO.class).getName()).bind(getModel());
		deviceForm.add(new FormControlTextFieldPanel<>("deviceNamePanel", deviceNameModel, deviceNameLabelModel, true));
	}

	private void addDeviceTypeComponent(Form<DeviceDTO> deviceForm) {
		IModel<String> deviceTypeLabelModel = new ResourceModel("device.type.text");
		IModel<DeviceType> deviceTypeModel = model(from(DeviceDTO.class).getType()).bind(getModel());
		deviceForm.add(new FormControlDropDownPanel<>("deviceTypePanel", deviceTypeModel, new ListModel<>(asList(DeviceType.values())),
		        new DeviceTypeChoiceRenderer(), deviceTypeLabelModel, true));
	}

	private void addDeviceUrlComponent(Form<DeviceDTO> deviceForm) {
		IModel<String> deviceUrlLabelModel = new ResourceModel("device.url.text");
		IModel<String> deviceUrlModel = model(from(DeviceDTO.class).getUrl()).bind(getModel());
		deviceForm.add(new FormControlTextFieldPanel<>("deviceUrlPanel", deviceUrlModel, deviceUrlLabelModel, true));
	}
}
