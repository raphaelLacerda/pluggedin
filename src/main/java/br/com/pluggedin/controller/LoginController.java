package br.com.pluggedin.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.pluggedin.IndexController;
import br.com.pluggedin.component.UserInfo;
import br.com.pluggedin.dao.UserDAO;
import br.com.pluggedin.model.User;

@Resource
public class LoginController {

	private final UserDAO	dao;
	private final Result	result;
	private final UserInfo	userInfo;

	public LoginController(UserDAO dao, Result result, UserInfo userInfo) {

		this.dao = dao;
		this.result = result;
		this.userInfo = userInfo;

	}

	public void login() {

	}

	@Path("/login")
	public void login(User user) {

		User findUser = dao.findUserByLogin(user.getLogin());
		if (findUser == null) {
			result.include("msg", "User not Found");
			result.forwardTo(this).login();
		}

		userInfo.login(findUser);

	}

	public void logout() {

		userInfo.logout();
		result.redirectTo(IndexController.class).index();
	}
}
