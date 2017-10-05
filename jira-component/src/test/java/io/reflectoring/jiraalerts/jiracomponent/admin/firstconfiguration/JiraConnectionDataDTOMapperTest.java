package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class JiraConnectionDataDTOMapperTest {

	private static final Long ID = 1337L;
	private static final Date MODIFIED_AT = new Date();
	private static final String URL = "http://test.test";
	private static final String USERNAME = "Testuser";
	private static final String PASSWORD = "password";

	private JiraConnectionDataDTOMapper sut = new JiraConnectionDataDTOMapper();
	private JiraConnectionDataDTO jiraConnectionDataDTO;
	private JiraConnectionData jiraConnectionData;

	@Before
	public void setup() {
		jiraConnectionDataDTO = new JiraConnectionDataDTO();
		jiraConnectionDataDTO.setId(ID);
		jiraConnectionDataDTO.setModifiedAt(MODIFIED_AT);
		jiraConnectionDataDTO.setUrl(URL);
		jiraConnectionDataDTO.setUsername(USERNAME);

		jiraConnectionData = new JiraConnectionData();
		jiraConnectionData.setId(ID);
		jiraConnectionData.setModifiedAt(MODIFIED_AT);
		jiraConnectionData.setUrl(URL);
		jiraConnectionData.setUsername(USERNAME);
	}

	@Test
	public void mapToEntityWithID() {
		JiraConnectionData jiraConnectionData = sut.dtoToEntity(jiraConnectionDataDTO);

		assertThat(jiraConnectionData.getId()).isEqualTo(ID);
	}

	@Test
	public void mapToEntityWithModifiedAt() {
		JiraConnectionData jiraConnectionData = sut.dtoToEntity(jiraConnectionDataDTO);

		assertThat(jiraConnectionData.getModifiedAt()).isEqualTo(MODIFIED_AT);
	}

	@Test
	public void mapToEntityWithURL() {
		JiraConnectionData jiraConnectionData = sut.dtoToEntity(jiraConnectionDataDTO);

		assertThat(jiraConnectionData.getUrl()).isEqualTo(URL);
	}

	@Test
	public void mapToEntityWithUsername() {
		JiraConnectionData jiraConnectionData = sut.dtoToEntity(jiraConnectionDataDTO);

		assertThat(jiraConnectionData.getUsername()).isEqualTo(USERNAME);
	}

	@Test
	public void mapToDTOWithID() {
		JiraConnectionDataDTO jiraConnectionDataDTO = sut.entityToDTO(jiraConnectionData);

		assertThat(jiraConnectionDataDTO.getId()).isEqualTo(ID);
	}

	@Test
	public void mapToDTOWithModifiedAt() {
		JiraConnectionDataDTO jiraConnectionDataDTO = sut.entityToDTO(jiraConnectionData);

		assertThat(jiraConnectionDataDTO.getModifiedAt()).isEqualTo(MODIFIED_AT);
	}

	@Test
	public void mapToDTOWithURL() {
		JiraConnectionDataDTO jiraConnectionDataDTO = sut.entityToDTO(jiraConnectionData);

		assertThat(jiraConnectionDataDTO.getUrl()).isEqualTo(URL);
	}

	@Test
	public void mapToDTOWithUsername() {
		JiraConnectionDataDTO jiraConnectionDataDTO = sut.entityToDTO(jiraConnectionData);

		assertThat(jiraConnectionDataDTO.getUsername()).isEqualTo(USERNAME);
	}
}
