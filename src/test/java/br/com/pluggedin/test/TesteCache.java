package br.com.pluggedin.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import br.com.pluggedin.domain.model.Music;

public class TesteCache extends AbstractTest{

	public static void main(String[] args) throws InterruptedException {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();
		Music music = (Music) session.get(Music.class, 1L);

		System.out.println(music.getName());
		Session session1 = factory.openSession();

				
		tx.commit();
		
		
		Transaction tx1 = session1.beginTransaction();
		
		Music music1 = (Music) session1.get(Music.class, 1L);
		System.out.println(music1.getName());

		music1.setName("music1");

		tx1.commit();
		System.out.println(music == music1);
		System.out.println(music.equals(music1));

		System.out.println(session.contains(music));
		System.out.println(session.contains(music1));

		System.out.println(session1.contains(music));
		System.out.println(session1.contains(music1));
	}
}
