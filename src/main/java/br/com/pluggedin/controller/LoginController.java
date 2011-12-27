package br.com.pluggedin.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.pluggedin.component.UserLogged;
import br.com.pluggedin.model.User;
import br.com.pluggedin.repository.UserRepository;

@Resource
public class LoginController {

	private final UserRepository	dao;
	private final Result			result;
	private final UserLogged		userInfo;
	private final Validator			validator;

	public LoginController(UserRepository dao, Result result, UserLogged userInfo, Validator validator) {

		this.dao = dao;
		this.result = result;
		this.userInfo = userInfo;
		this.validator = validator;

	}

	@Path("/login")
	public void login() {

		if (userInfo.isLogged()) {
			result.redirectTo(IndexController.class).index();
		}
	}

	public void login(User user) {

		final User currentUser = dao.findUserByLoginAndPassword(user);

		validate(currentUser);

		keepUser(currentUser);

	}

	private void keepUser(final User currentUser) {

		userInfo.login(currentUser);
		result.redirectTo(MusicController.class).list();
	}

	private void validate(final User currentUser) {

		validator.checking(new Validations() {

			{
				that(currentUser, is(notNullValue()), "login", "invalid_login_or_password");
			}
		});
		validator.onErrorUsePageOf(this).login();
	}

	@Path("/logout")
	public void logout() {

		userInfo.logout();
		result.redirectTo(IndexController.class).index();
	}
}
