package br.com.pluggedin.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import br.com.pluggedin.domain.model.User;

public class UserTest {

	private User	user;

	@Before
	public void initTest() {

		user = new User();
		user.addRole("admin", "user", "super");

	}

	@Test
	public void userRoles() {

		assertTrue(user.hasRole("admin"));
		assertTrue(user.hasRole("super"));
		assertFalse(user.hasRole("admin123123"));
	}

	@Test
	public void whenUserDoesntHaveRoles() {

		user = new User();

		assertFalse(user.hasRole("admin"));
		assertFalse(user.hasRole("super"));
		assertFalse(user.hasRole("admin123123"));
	}

}
