package io.reflectoring.jiraalerts.routine;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import io.reflectoring.jiraalerts.routine.RoutineQuery;
import io.reflectoring.jiraalerts.routine.RoutineQueryRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import io.reflectoring.jiraalerts.testsetup.AbstractDbUnitTest;

@DatabaseSetup("RoutineQueryRepositoryTest.xml")
public class RoutineQueryRepositoryTest extends AbstractDbUnitTest {

	private static final long USER_ID_1 = 1;
	private static final long USER_ID_2 = 2;

	@Autowired
	private RoutineQueryRepository testSubject;

	@Test
	public void findByOwnerFindsFourRoutinesForUserOne() throws Exception {
		List<RoutineQuery> routineQueries = testSubject.findByOwner(USER_ID_1, PageRequest.of(0, 10, Sort.DEFAULT_DIRECTION, "id"));

		assertThat(routineQueries).hasSize(4);
	}

	@Test
	public void findByOwnerFindsTwoRoutinesForUserTwo() throws Exception {
		List<RoutineQuery> routineQueries = testSubject.findByOwner(USER_ID_2, PageRequest.of(0, 10, Sort.DEFAULT_DIRECTION, "id"));

		assertThat(routineQueries).hasSize(2);
	}

	@Test
	public void countByOwnerReturnsFourForUserOne() throws Exception {
		assertThat(testSubject.countByOwner(USER_ID_1)).isEqualTo(4);
	}

	@Test
	public void countByOwnerReturnsTwoForUserTwo() throws Exception {
		assertThat(testSubject.countByOwner(USER_ID_2)).isEqualTo(2);
	}

	@Test
	public void findAllActiveReturnsThree() throws Exception {
		assertThat(testSubject.findAllActive()).hasSize(3);
	}
}
