package br.com.pluggedin.domain.repository;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

public abstract class AbstractRepository {

	private final Session	session;

	public AbstractRepository(Session session) {

		this.session = session;

	}

	protected FullTextSession	fullTextSession;
	protected QueryBuilder		queryBuilder;

	protected void createFullTextSessionAndBuilder() {

		if (fullTextSession == null) {
			this.fullTextSession = Search.getFullTextSession(session);
			createBuilder();

		}
	}

	protected void createBuilder() {

		if (queryBuilder == null)
			this.queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(getClassToSearch()).get();
	}

	protected abstract Class<?> getClassToSearch();

}
