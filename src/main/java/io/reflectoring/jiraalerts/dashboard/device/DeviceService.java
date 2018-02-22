package io.reflectoring.jiraalerts.dashboard.device;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.reflectoring.jiraalerts.application.login.User;

/**
 * Service methods for storing and loading a {@link Device}.
 */
@Service
@Transactional
public class DeviceService {

	@Inject
	private DeviceRepository deviceRepository;

	/**
	 * @param userId
	 *            the Id of a user which entries should be counted.
	 * @param pageRequest
	 *            the pagable for the repository-call.
	 * @return the entries of given user.
	 */
	public List<DeviceDTO> getDevicesByUserId(long userId, PageRequest pageRequest) {
		List<Device> devices = deviceRepository.findByOwner(userId, pageRequest);
		return mapAllFromEntityToDTO(devices);
	}

	/**
	 * Counts the entries by its owner.
	 *
	 * @param userId
	 *            the Id of a user which entries should be counted.
	 * @return count of entries of the given user.
	 */
	public int countDevicesByUserId(long userId) {
		return deviceRepository.countByOwner(userId);
	}

	/**
	 * Saves the device in the database.
	 *
	 * @param device
	 *            the device to save.
	 * @param userId
	 *            the Id of the user to whom the device belongs to.
	 */
	public void createDevice(DeviceDTO device, long userId) {
		Device entity = mapFromDTOToEntity(device);
		User owner = new User();
		owner.setId(userId);
		entity.setOwner(owner);
		deviceRepository.save(entity);
	}

	private List<DeviceDTO> mapAllFromEntityToDTO(List<Device> devices) {
		List<DeviceDTO> deviceDTOs = new ArrayList<>();
		devices.forEach(device -> deviceDTOs.add(mapFromEntityToDTO(device)));
		return deviceDTOs;
	}

	private DeviceDTO mapFromEntityToDTO(Device device) {
		DeviceDTO deviceDTO = new DeviceDTO();
		deviceDTO.setId(device.getId());
		deviceDTO.setName(device.getName());
		deviceDTO.setUrl(device.getUrl());
		deviceDTO.setType(device.getType());
		return deviceDTO;
	}

	private Device mapFromDTOToEntity(DeviceDTO device) {
		Device entity = new Device();
		entity.setId(device.getId());
		entity.setName(device.getName());
		entity.setUrl(device.getUrl());
		entity.setType(device.getType());
		return entity;
	}

}
