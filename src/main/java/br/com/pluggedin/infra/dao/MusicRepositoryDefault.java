package br.com.pluggedin.infra.dao;

import java.util.List;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import br.com.caelum.vraptor.ioc.Component;
import br.com.pluggedin.domain.model.Music;
import br.com.pluggedin.domain.repository.MusicRepository;
import br.com.pluggedin.infra.dao.base.AbstractRepository;
import br.com.pluggedin.infra.dao.base.DAO;

@Component
@SuppressWarnings("unchecked")
public class MusicRepositoryDefault extends AbstractRepository implements MusicRepository {

	private final Session	session;
	private DAO<Music>		dao;

	public MusicRepositoryDefault(Session session) {

		super(session);
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

	@Override
	public List<Music> findMusics(String music) {

		createFullTextSessionAndBuilder();
		Query query = queryBuilder.keyword().fuzzy().withThreshold(0.3f).onField("name").boostedTo(3f).andField("tags.name").boostedTo(2.5f)
				.andField("artist").boostedTo(2f).andField("description").boostedTo(1f).matching(music).createQuery();
		return fullTextSession.createFullTextQuery(query, Music.class).list();
	}

	@Override
	public List<Music> findMusicsWithName(String name) {

		createFullTextSessionAndBuilder();
		Query query = queryBuilder.keyword().fuzzy().withThreshold(0.2f).onField("name").matching(name).createQuery();
		return fullTextSession.createFullTextQuery(query, Music.class).list();
	}

	@Override
	public List<Music> findMusicsFromArtist(String artist) {

		createFullTextSessionAndBuilder();
		Query query = queryBuilder.keyword().fuzzy().withThreshold(0.2f).onField("artist").matching(artist).createQuery();
		return fullTextSession.createFullTextQuery(query, Music.class).list();
	}

	@Override
	protected Class<?> getClassToSearch() {

		return Music.class;
	}

}
