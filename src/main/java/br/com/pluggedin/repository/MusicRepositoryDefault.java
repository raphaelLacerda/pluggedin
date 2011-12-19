package br.com.pluggedin.repository;

import java.util.List;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import br.com.caelum.vraptor.ioc.Component;
import br.com.pluggedin.model.Music;

@Component
@SuppressWarnings("unchecked")
public class MusicRepositoryDefault implements MusicRepository {

	private final Session	session;
	private DAO<Music>		dao;

	public MusicRepositoryDefault(Session session) {

		this.session = session;
		this.dao = new DAO<Music>(Music.class, session);
	}

	@Override
	public List<Music> listAllMusics() {

		return dao.list();
	}

	@Override
	public List<Music> listAllMusicsOfUser(String user) {

		return session.createCriteria(Music.class).add(Restrictions.eq("user.login", user)).setCacheable(true).list();
	}

	@Override
	public List<Music> listMusicsOfUserOnDemand(int first, int max) {

		return session.createCriteria(Music.class).setMaxResults(max).setFirstResult(first).addOrder(Order.desc("dateRecorded")).list();
	}

	@Override
	public List<Music> listTheLastMusicsAdded(int max) {

		return session.createCriteria(Music.class).setMaxResults(max).addOrder(Order.desc("dateRecorded")).setCacheable(true).list();
	}

	@Override
	public void saveMusic(Music music) {

		dao.saveOrUpdate(music);

	}

	private FullTextSession	fullTextSession;
	private QueryBuilder	queryBuilder;

	@Override
	public List<Music> findMusics(String music) {

		createFullTextSessionAndBuilder();
		Query query = queryBuilder.keyword().onFields("artist", "description", "name").matching(music).createQuery();
		return fullTextSession.createFullTextQuery(query, Music.class).list();
	}

	@Override
	public List<Music> findMusicsWithName(String name) {

		createFullTextSessionAndBuilder();
		Query query = queryBuilder.keyword().fuzzy().withThreshold(0.3f).onField("name").matching(name).createQuery();
		return fullTextSession.createFullTextQuery(query, Music.class).list();
	}

	@Override
	public List<Music> findMusicsFromArtist(String artist) {

		createFullTextSessionAndBuilder();
		Query query = queryBuilder.keyword().fuzzy().withThreshold(0.3f).onField("artist").matching(artist).createQuery();
		return fullTextSession.createFullTextQuery(query, Music.class).list();
	}

	private void createFullTextSessionAndBuilder() {

		if (fullTextSession == null) {
			this.fullTextSession = Search.getFullTextSession(session);
			createBuilder();

		}
	}

	private void createBuilder() {

		if (queryBuilder == null)
			this.queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Music.class).get();
	}

}
