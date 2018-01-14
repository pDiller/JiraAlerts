package io.reflectoring.jiraalerts.dashboard.device;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

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
		setSort("name", SortOrder.DESCENDING);
	}

	@Override
	public Iterator<? extends DeviceDTO> iterator(long first, long count) {
		// TODO
		return null;
	}

	@Override
	public long size() {
		// TODO
		return 0;
	}

	@Override
	public IModel<DeviceDTO> model(DeviceDTO object) {
		return Model.of(object);
	}
}
