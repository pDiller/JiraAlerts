package io.reflectoring.jiraalerts.login;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class HashServiceTest {

	private static final String PASSWORD = "Test123_";
	private static final String SALT = "askfdinfe123iufas";
	private static final String HASHED_PASSWORD = "b5716aa5d714319ef943a635b5848a272868185560f2e2efe9154a60683edac8341e320257dd2d93347873f7cebde61237fa9828428acf1a748ee8da94f58bf1";

	private HashService testSubject = new HashService();

	@Test
	public void hashPasswordReturnsHashedValueOfPassword() throws Exception {
		String hashedPassword = testSubject.hashPassword(PASSWORD, SALT);

		assertThat(hashedPassword).isEqualTo(HASHED_PASSWORD);
	}

	@Test
	public void hashPasswordWithWrongSaltReturnsWrongHashvalue() throws Exception {
		String hashedPassword = testSubject.hashPassword(PASSWORD, "WrongSalt");

		assertThat(hashedPassword).isNotEqualTo(HASHED_PASSWORD);
	}
}
