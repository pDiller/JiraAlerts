package io.reflectoring.jiraalerts.dashboard.device;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Provides existing devices.
 */
public class DeviceDataProvider extends SortableDataProvider<DeviceDTO, String> {

	@Inject
	private DeviceService deviceService;

	private long userId;
	private int rowsPerPage;

	/**
	 * Construcotr.
	 *
	 * @param userId
	 *            the Id of the user who is loggedin.
	 * @param rowsPerPage
	 *            the number of entries per page.
	 */
	public DeviceDataProvider(long userId, int rowsPerPage) {
		this.userId = userId;
		this.rowsPerPage = rowsPerPage;
		Injector.get().inject(this);
		setSort("name", SortOrder.ASCENDING);
	}

	@Override
	public Iterator<? extends DeviceDTO> iterator(long first, long count) {
		return deviceService.getDevicesByUserId(userId, createPageRequest(first)).iterator();
	}

	@Override
	public long size() {
		return deviceService.countDevicesByUserId(userId);
	}

	@Override
	public IModel<DeviceDTO> model(DeviceDTO object) {
		return Model.of(object);
	}

	private PageRequest createPageRequest(long first) {
		Sort.Direction sortDirection = getSort().isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
		return PageRequest.of((int) (first / rowsPerPage), rowsPerPage, sortDirection, getSort().getProperty());
	}
}
