package io.reflectoring.jiraalerts.login;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Inject
	private UserRepository userRepository;

	public UserDTO findUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			UserDTO userDTO = new UserDTO();
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setSalt(user.getSalt());
			return userDTO;
		}
		return null;
	}
}
