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
		User user = new User("teste");
//		user = (User) session.load(User.class, "login");
		user.setPassword("123");
		user.setName("teste");
		user.setEmail("rafa@gac.com");
		session.saveOrUpdate(user);
		session.flush();

		Music music = new Music();
		music.setName("cqc");
		music.setArtist("artist");
		music.setDescription("raphael henrique lacerda");
		music.setUser(user);
		music.setTags("programa band rafinha");

		session.saveOrUpdate(music);
		tx.commit();

		System.out.println(music);

	}
}
