package br.com.pluggedin.dao;

import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;


@Component
public class DAO<T> {

	private final Class<T>	classe;
	private final Session	session;

	public DAO(Class<T> classe, Session session) {

		this.classe = classe;
		this.session = session;

	}

	@SuppressWarnings("unchecked")
	public T findById(String id) {

		return (T) session.load(classe, id);

	}

	@SuppressWarnings("unchecked")
	public T findById(Integer id) {

		return (T) session.load(classe, id);
	}
}
