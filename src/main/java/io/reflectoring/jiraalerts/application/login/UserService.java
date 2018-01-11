package io.reflectoring.jiraalerts.application.login;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * Service for user methods.
 */
@Service
public class UserService {

	@Inject
	private UserRepository userRepository;

	/**
	 * Searchs the user by it´s username.
	 *
	 * @param username
	 *            the username of the searched user.
	 * @return the mapped {@link UserDTO}, when a user is found. Otherwise <code>null</code>
	 */
	public UserDTO findUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setSalt(user.getSalt());
			return userDTO;
		}
		return null;
	}
}
