package br.com.pluggedin.test;

import java.util.List;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import br.com.pluggedin.model.Music;

public class TesteLucene {

	public static void main(String[] args) throws InterruptedException {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		FullTextSession fullTextSession = Search.getFullTextSession(session);

		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Music.class).get();
		Query query = queryBuilder.keyword().onFields("artist","description","name").matching("ab").createQuery();
//		Query query = queryBuilder.keyword().onField("name").matching("bre").createQuery();
//		Query query = queryBuilder.keyword().fuzzy().withThreshold(0.1f).onField("name").matching("be").createQuery();
		
		@SuppressWarnings("unchecked")
		List<Music> list = fullTextSession.createFullTextQuery(query, Music.class).list();
		
		System.out.println(list);
		System.exit(0);
	}
}
