package br.com.pluggedin.test;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import br.com.pluggedin.domain.model.Music;
import br.com.pluggedin.infra.MusicRepositoryDefault;

public class TesteCache {

	public static void main(String[] args) throws InterruptedException {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();

		MusicRepositoryDefault musicRepositoryDefault = new MusicRepositoryDefault(session);

		List<Music> listAllMusicsOfUser = musicRepositoryDefault.listAllMusicsOfUser("login");
		System.out.println(listAllMusicsOfUser);

		Session session1 = factory.openSession();

		MusicRepositoryDefault musicRepositoryDefault1 = new MusicRepositoryDefault(session1);
		List<Music> listAllMusicsOfUser2 = musicRepositoryDefault1.listAllMusicsOfUser("login");
		System.out.println(listAllMusicsOfUser2);
	}
}
