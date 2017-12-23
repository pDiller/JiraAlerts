package io.reflectoring.jiraalerts.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the {@link User}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Finds a user by it´s username.
	 * 
	 * @param username
	 *            the username of the searched user.
	 * @return The user entity from database.
	 */
	User findByUsername(String username);
}
