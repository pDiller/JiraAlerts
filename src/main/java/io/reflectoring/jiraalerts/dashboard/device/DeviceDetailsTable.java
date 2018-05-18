package io.reflectoring.jiraalerts.dashboard.device;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.ResourceModel;

/**
 * Table for device details
 */
public class DeviceDetailsTable extends AjaxFallbackDefaultDataTable<DeviceDTO, String> {

	private static final int ROWS_PER_PAGE = 12;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            Wicket-Id.
	 * @param userId
	 *            the Id of the user which is loggedin.
	 */
	public DeviceDetailsTable(String id, long userId) {
		super(id, createColumns(), new DeviceDataProvider(userId, ROWS_PER_PAGE), ROWS_PER_PAGE);
	}

	private static List<? extends IColumn<DeviceDTO, String>> createColumns() {
		List<IColumn<DeviceDTO, String>> columns = new ArrayList<>();
		columns.add(new PropertyColumn<>(new ResourceModel("device.table.name.column"), "name", "name"));
		columns.add(new PropertyColumn<>(new ResourceModel("device.table.url.column"), "url", "url"));
		columns.add(new PropertyColumn<>(new ResourceModel("device.table.type.column"), "type", "type"));
		return columns;
	}

}
