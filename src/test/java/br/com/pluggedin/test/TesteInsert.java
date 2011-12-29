package br.com.pluggedin.test;

import java.util.Arrays;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import br.com.pluggedin.model.Chord;
import br.com.pluggedin.model.Music;
import br.com.pluggedin.model.Playlist;
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
		music.setTags("programa, band, rafinha");

		Chord chord = new Chord();
		chord.setUrlChord("cifra1");
		Chord chord1 = new Chord();
		chord1.setUrlChord("cifra2");
		music.setChords(Arrays.asList(chord, chord1));

		session.saveOrUpdate(music);

		Playlist playlist = new Playlist();
		playlist.setCategory("rock");
		playlist.setUser(user);
		playlist.setName("lista1");
		playlist.setDescription("lista para tocar em bar");
		playlist.setMusics(Arrays.asList(music));

		session.saveOrUpdate(playlist);
		tx.commit();

		System.out.println(music);

	}
}
