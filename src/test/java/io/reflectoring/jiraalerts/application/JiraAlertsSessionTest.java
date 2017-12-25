package io.reflectoring.jiraalerts.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.login.HashService;
import io.reflectoring.jiraalerts.login.UserDTO;
import io.reflectoring.jiraalerts.login.UserNotLoggedInException;
import io.reflectoring.jiraalerts.login.UserService;

@RunWith(MockitoJUnitRunner.class)
public class JiraAlertsSessionTest {

	private static final String TESTNAME = "TestName";
	private static final String TEST_PASSWORD = "TestPassword";
	private static final String TEST_SALT = "TestSalt";

	@Mock
	private UserService userServiceMock;

	@Mock
	private HashService hashServiceMock;

	private JiraAlertsSession testSubject;

	@Before
	public void setUp() throws Exception {
		new WicketTester(new TestApplication(this));
		testSubject = new JiraAlertsSession(RequestCycle.get().getRequest());
	}

	@Test
	public void whenNoUserIsFoundForUsernameExceptionIsThrown() throws Exception {
		try {
			testSubject.authenticate("Test", "Test");
		} catch (UserNotLoggedInException exception) {
			assertThat(exception.getMessage()).isEqualTo("No user found in database for username 'Test'");
		}
	}

	@Test
	public void whenUserIsFoundButPasswordIsIncorrectExceptionIsThrown() throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(TESTNAME);
		userDTO.setPassword(TEST_PASSWORD);
		userDTO.setSalt(TEST_SALT);
		when(userServiceMock.findUserByUsername(TESTNAME)).thenReturn(userDTO);
		try {
			testSubject.authenticate(TESTNAME, "Test");
		} catch (UserNotLoggedInException exception) {
			assertThat(exception.getMessage()).isEqualTo("Password invalid for username 'TestName'");
		}
	}

	@Test
	public void userIsFoundAndPasswordIsCorrectReturnsTrue() throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setPassword(TEST_PASSWORD);
		userDTO.setSalt(TEST_SALT);
		when(userServiceMock.findUserByUsername(TESTNAME)).thenReturn(userDTO);
		when(hashServiceMock.hashPassword(TEST_PASSWORD, TEST_SALT)).thenReturn(TEST_PASSWORD);

		boolean authenticated = testSubject.authenticate(TESTNAME, TEST_PASSWORD);

		assertThat(authenticated).isTrue();
	}

	@Test
	public void whenAuthenticationIsSuccessfullAdministratorRoleIsSet() throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setPassword(TEST_PASSWORD);
		userDTO.setSalt(TEST_SALT);
		when(userServiceMock.findUserByUsername(TESTNAME)).thenReturn(userDTO);
		when(hashServiceMock.hashPassword(TEST_PASSWORD, TEST_SALT)).thenReturn(TEST_PASSWORD);

		testSubject.authenticate(TESTNAME, TEST_PASSWORD);

		assertThat(testSubject.getRoles()).contains("administrator");
	}
}