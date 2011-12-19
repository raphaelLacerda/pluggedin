package br.com.pluggedin.controller;

import java.util.List;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.pluggedin.model.User;
import br.com.pluggedin.repository.UserRepository;

@Resource
public class UserController {

	private final Result				result;
	private final Validator				validator;
	private final UserRepository	userRepo;

	public UserController(UserRepository userRepo, Result result, Validator validator) {

		this.userRepo = userRepo;
		this.result = result;
		this.validator = validator;

	}

	@Path("/register")
	public void register() {

	}

	@Path("/users")
	public List<User> list() {

		return userRepo.listAllUsers();
	}

	@Post
	public void save(User user) {

		//TODO: the password must be recorded encrypted
		validar(user);
		userRepo.saveUser(user);
		result.redirectTo(this).list();
	}

	private void validar(User user) {

		validator.validate(user);
		validator.onErrorUsePageOf(this).list();

	}
}
