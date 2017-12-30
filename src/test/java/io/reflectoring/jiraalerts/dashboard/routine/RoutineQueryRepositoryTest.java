package io.reflectoring.jiraalerts.dashboard.routine;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import io.reflectoring.jiraalerts.application.login.User;
import io.reflectoring.jiraalerts.application.login.UserRepository;
import io.reflectoring.jiraalerts.application.testsetup.AbstractDbUnitTest;

@DatabaseSetup("RoutineQueryRepositoryTest.xml")
public class RoutineQueryRepositoryTest extends AbstractDbUnitTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoutineQueryRepository testSubject;

	@Test
	public void findByOwnerFindsFourRoutinesForUserOne() throws Exception {
		User user = userRepository.findOne(1L);
		List<RoutineQuery> routineQueries = testSubject.findByOwner(user);

		assertThat(routineQueries).hasSize(4);
	}

    @Test
    public void findByOwnerFindsTwoRoutinesForUserTwo() throws Exception {
        User user = userRepository.findOne(2L);
        List<RoutineQuery> routineQueries = testSubject.findByOwner(user);

        assertThat(routineQueries).hasSize(2);
    }

    @Test
    public void countByOwnerReturnsFourForUserOne() throws Exception {
        User user = userRepository.findOne(1L);
	    assertThat(testSubject.countByOwner(user)).isEqualTo(4);
	}

    @Test
    public void countByOwnerReturnsTwoForUserTwo() throws Exception {
        User user = userRepository.findOne(2L);
        assertThat(testSubject.countByOwner(user)).isEqualTo(2);
    }
}
