package io.reflectoring.jiraalerts.jiracomponent.admin.firstconfiguration;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class FirstConfigurationMapperTest {

	private static final Long ID = 42L;
	private static final Date DATE = new Date();

	private FirstConfigurationMapper sut = new FirstConfigurationMapper();
	private FirstConfigurationDTO firstConfigurationDTO;

	@Before
	public void setUp() throws Exception {
		firstConfigurationDTO = new FirstConfigurationDTO();
		firstConfigurationDTO.setId(ID);
		firstConfigurationDTO.setConfiguredAt(DATE);
	}

	@Test
	public void mapsIDCorrectly() throws Exception {
		FirstConfiguration firstConfiguration = sut.dtoToEntity(firstConfigurationDTO);

		assertThat(firstConfiguration.getId()).isEqualTo(ID);
	}

	@Test
	public void mapsConfiguredAtCorrectly() throws Exception {
		FirstConfiguration firstConfiguration = sut.dtoToEntity(firstConfigurationDTO);

		assertThat(firstConfiguration.getConfiguredAt()).isEqualTo(DATE);
	}
}
