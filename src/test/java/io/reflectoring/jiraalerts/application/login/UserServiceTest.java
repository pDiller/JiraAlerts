package io.reflectoring.jiraalerts.application.login;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

		assertThat(loadedUserDTO.getUsername()).isEqualTo(TEST_NAME);
	}

	@Test
	public void returnsNullWhenRepositoryDontFindUser() throws Exception {
		UserDTO loadedUserDTO = testSubject.findUserByUsername(TEST_NAME);

		assertThat(loadedUserDTO).isNull();
	}

	@Test
	public void findUserByUsernameCallsUserRepository() throws Exception {
		testSubject.findUserByUsername(TEST_NAME);

		verify(userRepositoryMock).findByUsername(TEST_NAME);
	}
}
