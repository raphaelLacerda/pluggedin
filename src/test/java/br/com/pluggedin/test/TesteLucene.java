package br.com.pluggedin.test;

import java.util.List;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import br.com.pluggedin.domain.model.Music;

public class TesteLucene extends AbstractTest{

	public static void main(String[] args) throws InterruptedException {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		FullTextSession fullTextSession = Search.getFullTextSession(session);

		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Music.class).get();
//		Query query = queryBuilder.keyword().onFields("artist","description","name").matching("cae").createQuery();
//		Query query = queryBuilder.keyword().fuzzy().withThreshold(0.3f).onField("name").boostedTo(2f).andField("artist").boostedTo(2f)
//				.andField("tags").boostedTo(2f).andField("description").boostedTo(1f).matching("bes").createQuery();
//		Query query = queryBuilder.keyword().onField("tags").matching("bes").createQuery();
		Query query = queryBuilder.keyword().fuzzy().withThreshold(0.1f).onField("tags.name").matching("rafa").createQuery();

		@SuppressWarnings("unchecked")
		List<Music> list = fullTextSession.createFullTextQuery(query, Music.class).list();

		System.out.println(list);
		System.exit(0);
	}
}
