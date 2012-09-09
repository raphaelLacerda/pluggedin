package br.com.pluggedin.infra.components;


import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class HibernateTransactionInterceptor implements Interceptor {

	private final Session session;
	private final Validator validator;

	public HibernateTransactionInterceptor(Session session, Validator validator) {
		this.session = session;
		this.validator = validator;
	}

    //TODO I think that transaction null check is unnecessary, since we never get null transation (garcia-jj)
	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			stack.next(method, instance);
			if (!validator.hasErrors() && transaction != null) {
				transaction.commit();
			}
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	public boolean accepts(ResourceMethod method) {
		return true; // Will intercept all requests
	}
}