package io.reflectoring.jiraalerts.jiracomponent.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.wicket.model.Model;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.reflectoring.jiraalerts.base.wickettests.TestConfiguration;
import io.reflectoring.jiraalerts.jiracomponent.wickettests.JiraComponentTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JiraComponentTestConfiguration.class, TestConfiguration.class })
public class LoadJiraConnectionUrlModelTest {

	private static final String TEST_URL = "http://test.org";
	private static final long TEST_ID = 1337L;

	@Autowired
	private JiraConnectionConfigurationService jiraConnectionConfigurationServiceMock;

	private LoadJiraConnectionUrlModel sut;

	@Before
	public void setUp() {
		when(jiraConnectionConfigurationServiceMock.loadConnectionUrl(TEST_ID)).thenReturn(TEST_URL);

		sut = new LoadJiraConnectionUrlModel(Model.of(TEST_ID));
	}

	@Test
	public void getObjectLoadsCorrectData() {
		String loadedConnectionUrl = sut.getObject();

		assertThat(loadedConnectionUrl).isEqualTo(TEST_URL);
	}

	@Test
	public void getObjectCallsServiceMethodWithCorrectParameters() throws Exception {
		sut.getObject();

		verify(jiraConnectionConfigurationServiceMock).loadConnectionUrl(TEST_ID);
	}
}
