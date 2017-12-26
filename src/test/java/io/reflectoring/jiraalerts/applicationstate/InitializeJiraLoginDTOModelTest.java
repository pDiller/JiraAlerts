package io.reflectoring.jiraalerts.applicationstate;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

import org.apache.wicket.util.tester.WicketTester;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;

@RunWith(MockitoJUnitRunner.class)
public class InitializeJiraLoginDTOModelTest {

	private static final String TEST_USERNAME = "USERNAME";
	private static final String TEST_URL = "URL";

	@Mock
	private JiraConnectionRepository jiraConnectionRepositoryMock;

	private InitializeJiraLoginDTOModel testSubject;

	@Before
	public void setUp() throws Exception {
		new WicketTester(new TestApplication(this));
		testSubject = new InitializeJiraLoginDTOModel();
	}

	@Test
	public void getObjectReturnsNewInitializedDataWhenRepositoryReturnsEmptyList() throws Exception {
		when(jiraConnectionRepositoryMock.findAll()).thenReturn(emptyList());

		JiraLoginDTO jiraLoginDTO = testSubject.getObject();

		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(jiraLoginDTO.getUsername()).isNull();
		softAssertions.assertThat(jiraLoginDTO.getUrl()).isNull();
		softAssertions.assertAll();
	}

	@Test
	public void getObjectReturnsInitializedData() throws Exception {
		JiraConnection jiraConnection = new JiraConnection();
		jiraConnection.setUsername(TEST_USERNAME);
		jiraConnection.setUrl(TEST_URL);
		when(jiraConnectionRepositoryMock.findAll()).thenReturn(singletonList(jiraConnection));

		JiraLoginDTO jiraLoginDTO = testSubject.getObject();

		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(jiraLoginDTO.getUsername()).isEqualTo(TEST_USERNAME);
		softAssertions.assertThat(jiraLoginDTO.getUrl()).isEqualTo(TEST_URL);
		softAssertions.assertAll();
	}
}
