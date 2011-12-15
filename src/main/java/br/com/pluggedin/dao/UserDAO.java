package br.com.pluggedin.dao;

import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.pluggedin.model.User;

@Component
public class UserDAO {

	@SuppressWarnings("unused")
	private final Session	session;

	private DAO<User>		dao;

	public UserDAO(Session session) {

		this.session = session;
		dao = new DAO<User>(User.class, session);
	}

	public User findUserByLogin(String login) {

		return dao.findById(login);
	}
}
