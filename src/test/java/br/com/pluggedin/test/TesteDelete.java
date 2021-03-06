package br.com.pluggedin.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Ignore;

import br.com.pluggedin.domain.model.Music;


@Ignore
public class TesteDelete extends AbstractTest{

	public static void main(String[] args) throws InterruptedException {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();
		tx.begin();
		Music load = (Music) session.load(Music.class, (long)1);
		session.delete(load);
		tx.commit();

		System.out.println(load);

	}
}
