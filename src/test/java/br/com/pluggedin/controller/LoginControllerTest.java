package br.com.pluggedin.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.pluggedin.component.UserLogged;
import br.com.pluggedin.model.User;
import br.com.pluggedin.repository.UserRepository;
import br.com.pluggedin.test.AbstractTest;

public class LoginControllerTest extends AbstractTest {

	@Mock
	private UserRepository	userRepo;
	private LoginController	controller;
	private UserLogged		userInfo;

	@Before
	public void initTests() {

		userInfo = new UserLogged();
		controller = new LoginController(userRepo, result, userInfo, validator);
	}

	@Test
	public void userLoggedInWhenThereIsValidUser() {

		User user = new User();
		user.setLogin("rafa");
		user.setPassword("123");
		when(userRepo.findUserByLoginAndPassword(user)).thenReturn(user);

		assertEquals(false, userInfo.isLogged());
		controller.login(user);
		assertEquals(true, userInfo.isLogged());
	}

	@Test(expected = ValidationException.class)
	public void userLoggedInButWhenUserNotFound() {

		User user = new User();
		user.setLogin("rafa");
		user.setPassword("123");
		when(userRepo.findUserByLoginAndPassword(user)).thenReturn(null);

		assertEquals(false, userInfo.isLogged());
		controller.login(user);

	}

	@Test
	public void userLoggedOut() {

		User user = new User();
		user.setLogin("rafa");
		user.setPassword("123");
		userInfo.login(user);
		assertEquals(true, userInfo.isLogged());
		userInfo.logout();
		assertEquals(false, userInfo.isLogged());

	}

}
