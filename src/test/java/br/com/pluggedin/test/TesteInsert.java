package br.com.pluggedin.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import br.com.pluggedin.model.Music;
import br.com.pluggedin.model.User;

public class TesteInsert {

	public static void main(String[] args) throws InterruptedException {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();
		tx.begin();
		User user = new User();
		user.setLogin("login");
//		user = (User) session.load(User.class, "login");
		
		Music music = new Music();
		music.setName("name");
		music.setArtist("artist");
		music.setDescription("raphael henrique lacerda");
		music.setUrlChord("dadasdas");
		music.addUser(user);

		session.persist(music);
		tx.commit();

		System.out.println(music);

	}
}
