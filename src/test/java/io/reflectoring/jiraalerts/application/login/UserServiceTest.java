package io.reflectoring.jiraalerts.application.login;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.user.User;
import io.reflectoring.jiraalerts.user.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private static final String TEST_NAME = "TestName";

	@Mock
	private UserRepository userRepositoryMock;

	@InjectMocks
	private UserService testSubject;

	@Test
	public void returnsUserDTOWhenRepositoryFindsUser() throws Exception {
		User user = new User();
		user.setUsername(TEST_NAME);
		when(userRepositoryMock.findByUsername(TEST_NAME)).thenReturn(user);

		UserDTO loadedUserDTO = testSubject.findUserByUsername(TEST_NAME);

		Assertions.assertThat(loadedUserDTO.getUsername()).isEqualTo(TEST_NAME);
	}

	@Test
	public void returnsNullWhenRepositoryDontFindUser() throws Exception {
		UserDTO loadedUserDTO = testSubject.findUserByUsername(TEST_NAME);

		Assertions.assertThat(loadedUserDTO).isNull();
	}

	@Test
	public void findUserByUsernameCallsUserRepository() throws Exception {
		testSubject.findUserByUsername(TEST_NAME);

		verify(userRepositoryMock).findByUsername(TEST_NAME);
	}
}
