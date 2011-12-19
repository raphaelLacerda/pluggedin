package br.com.pluggedin.repository;

import java.util.List;
import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.pluggedin.model.User;

@Component
public class UserRepositoryDefault implements UserRepository {

	private final Session	session;

	private DAO<User>		dao;

	public UserRepositoryDefault(Session session) {

		this.session = session;
		dao = new DAO<User>(User.class, session);
	}

	@Override
	public User findUserByLogin(String login) {

		return dao.findById(login);
	}

	@Override
	public User findUserByLoginAndPassword(User user) {

		return (User) session.createQuery("from User where login = :login and password = :pass").setParameter("login", user.getLogin())
				.setParameter("pass", user.getPassword()).uniqueResult();
	}

	@Override
	public void saveUser(User user) {

		dao.saveOrUpdate(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listAllUsers() {

		return dao.list();
	}

	@Override
	public void refreshUser(User user) {

		dao.refresh(user);
	}
}
