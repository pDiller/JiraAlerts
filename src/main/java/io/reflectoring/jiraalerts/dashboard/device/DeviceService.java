package io.reflectoring.jiraalerts.dashboard.device;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service methods for storing and loading a {@link Device}.
 */
@Service
@Transactional
public class DeviceService {

	/**
	 * @param userId
	 *            the Id of a user which entries should be counted.
	 * @param pageRequest
	 *            the pagable for the repository-call.
	 * @return the entries of given user.
	 */
	public List<DeviceDTO> getDevicesByUserId(long userId, PageRequest pageRequest) {
		return null;
	}

	/**
	 * Counts the entries by its owner.
	 *
	 * @param userId
	 *            the Id of a user which entries should be counted.
	 * @return count of entries of the given user.
	 */
	public int countDevicesByUserId(long userId) {
		return 0;
	}

}
