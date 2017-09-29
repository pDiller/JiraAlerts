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

	private static final long TEST_ID = 1337L;
	private JiraConnectionDataDTO TEST_DATA = new JiraConnectionDataDTO();

	@Autowired
	private JiraConnectionConfigurationService jiraConnectionConfigurationServiceMock;

	private LoadJiraConnectionUrlModel sut;

	@Before
	public void setUp() {
		TEST_DATA.setId(TEST_ID);
		when(jiraConnectionConfigurationServiceMock.loadJiraConnectionData(TEST_ID)).thenReturn(TEST_DATA);

		sut = new LoadJiraConnectionUrlModel(Model.of(TEST_ID));
	}

	@Test
	public void getObjectLoadsCorrectData() {
		JiraConnectionDataDTO loadedConnectionData = sut.getObject();

		assertThat(loadedConnectionData).isEqualTo(TEST_DATA);
	}

	@Test
	public void getObjectCallsServiceMethodWithCorrectParameters() throws Exception {
		sut.getObject();

		verify(jiraConnectionConfigurationServiceMock).loadJiraConnectionData(TEST_ID);
	}
}
