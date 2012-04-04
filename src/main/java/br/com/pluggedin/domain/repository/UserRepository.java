package br.com.pluggedin.domain.repository;

import java.util.List;
import br.com.pluggedin.domain.model.User;

public interface UserRepository {

	User findUserByLogin(String login);

	User findUserByLoginAndPassword(User user);

	void saveUser(User user);

	List<User> listAllUsers();

	void refreshUser(User user);

	List<User> findUsers(String user);

}