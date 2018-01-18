package io.reflectoring.jiraalerts.dashboard.device;

import java.util.Optional;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import io.reflectoring.jiraalerts.base.BasePage;

@AuthorizeInstantiation("administrator")
public class CreateDevicePage extends BasePage {

	public CreateDevicePage() {

		IModel<DeviceDTO> deviceDTOModel = new Model<>(new DeviceDTO());
		Form<DeviceDTO> createDeviceForm = new Form<>("createDeviceForm", deviceDTOModel);

		createDeviceForm.add(new DevicePanel("devicePanel", deviceDTOModel));

		createDeviceForm.add(new AjaxFallbackButton("submitButton", createDeviceForm) {

			@Override
			protected void onSubmit(Optional<AjaxRequestTarget> targetOptional) {
				super.onSubmit(targetOptional);
				targetOptional.ifPresent(target -> target.add(CreateDevicePage.this));
			}

			@Override
			protected void onError(Optional<AjaxRequestTarget> targetOptional) {
				super.onError(targetOptional);
				targetOptional.ifPresent(target -> target.add(CreateDevicePage.this));
			}
		});

		add(createDeviceForm);
	}

}
