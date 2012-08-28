package br.com.pluggedin.infra.dao.base;

import java.util.List;
import org.hibernate.Session;

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

	public void saveOrUpdate(T t) {

		session.saveOrUpdate(t);

	}

	@SuppressWarnings("rawtypes")
	public List list() {

		return session.createCriteria(classe).setCacheable(true).list();
	}

	public void refresh(T t) {

		session.refresh(t);
	}
}
