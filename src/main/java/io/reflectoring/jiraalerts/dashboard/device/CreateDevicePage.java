package io.reflectoring.jiraalerts.dashboard.device;

import java.util.Optional;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;

import io.reflectoring.jiraalerts.application.JiraAlertsSession;
import io.reflectoring.jiraalerts.base.BasePage;

@AuthorizeInstantiation("administrator")
public class CreateDevicePage extends BasePage {

	@Inject
	private DeviceService deviceService;

	private Form<DeviceDTO> createDeviceForm;
	private AjaxFallbackButton submitButton;

	public CreateDevicePage() {
		Long userId = JiraAlertsSession.get().getUserId();
		IModel<DeviceDTO> deviceDTOModel = new Model<>(new DeviceDTO());

		createDeviceForm = new Form<>("createDeviceForm", deviceDTOModel);
		createDeviceForm.add(new DevicePanel("devicePanel", deviceDTOModel));

		submitButton = new AjaxFallbackButton("submitButton", createDeviceForm) {

			@Override
			protected void onSubmit(Optional<AjaxRequestTarget> targetOptional) {
				super.onSubmit(targetOptional);
				deviceService.createDevice(createDeviceForm.getModelObject(), userId);
				RequestCycle.get().setResponsePage(DevicesDetailPage.class);
			}

			@Override
			protected void onError(Optional<AjaxRequestTarget> targetOptional) {
				super.onError(targetOptional);
				targetOptional.ifPresent(target -> target.add(CreateDevicePage.this));
			}
		};

		createDeviceForm.add(new AjaxFallbackButton("testUrlButton", createDeviceForm) {

			@Override
			protected void onSubmit(Optional<AjaxRequestTarget> targetOptional) {
				super.onSubmit(targetOptional);
				submitButton.setEnabled(deviceService.testUrl(createDeviceForm.getModelObject().getUrl()));
				targetOptional.ifPresent(target -> target.add(CreateDevicePage.this));
			}

			@Override
			protected void onError(Optional<AjaxRequestTarget> targetOptional) {
				super.onError(targetOptional);
				targetOptional.ifPresent(target -> target.add(CreateDevicePage.this));
			}
		});

		createDeviceForm.add(submitButton);

		add(createDeviceForm);
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		String deviceUrl = createDeviceForm.getModelObject().getUrl();
		submitButton.setEnabled(deviceService.testUrl(deviceUrl));
	}
}
