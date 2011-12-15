package br.com.pluggedin.controller;

import java.util.List;
import org.hibernate.Session;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.pluggedin.model.User;

@Resource
public class UserController {

	private final Session	session;
	private final Result	result;
	private final Validator	validator;

	public UserController(Session session, Result result, Validator validator) {

		this.session = session;
		this.result = result;
		this.validator = validator;

	}

	@SuppressWarnings("unchecked")
	@Path("/users")
	public List<User> list() {

		return session.createCriteria(User.class).list();
	}

	public void save(User user) {

		validar(user);
		session.saveOrUpdate(user);
		result.redirectTo(this).list();
	}

	private void validar(User user) {

		validator.validate(user);
		validator.onErrorForwardTo(this).list();

	}
}
