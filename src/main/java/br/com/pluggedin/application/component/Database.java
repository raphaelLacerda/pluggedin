package br.com.pluggedin.application.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.joda.time.DateTime;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.pluggedin.application.controller.UserController;
import br.com.pluggedin.application.security.Restrict;
import br.com.pluggedin.domain.model.Chord;
import br.com.pluggedin.domain.model.Music;
import br.com.pluggedin.domain.model.Playlist;
import br.com.pluggedin.domain.model.User;

@Resource
public class Database {

	private final Session	session;
	private final Validator	validator;
	private final Result	result;

	public Database(Session session, Validator validator, Result result) {

		this.session = session;
		this.validator = validator;
		this.result = result;
	}

	@Path("/admin/index")
	@Restrict(values = { "admin" })
	public void indexar() {

		FullTextSession fullTextSession = Search.getFullTextSession(session);
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			validator.add(new ValidationMessage("Not able to index database" + e, "db"));

		}
		result.redirectTo(UserController.class).list();
	}

	@Path("/admin/build")
	@Restrict(values = { "admin" })
	public void buildBase() {

		registerUsers();

		User user = new User("login1");
		registerMusics(user);
		registerPlaylist(user);

		result.redirectTo(UserController.class).list();
	}

	private void registerPlaylist(User user) {

		for (int i = 1; i <= 3; i++) {
			Playlist playlist = new Playlist();
			playlist.setCategory("rock" + i);
			playlist.setUser(user);
			playlist.setName("lista" + i);
			playlist.setDescription("lista para tocar em bar");

			List<Music> musics = new ArrayList<Music>();
			for (int j = 0; j < 3; j++) {
				long nextLong = 0;
				while (nextLong == 0) {
					nextLong = new Random().nextInt(11);
				}

				Music music = (Music) session.load(Music.class, nextLong);
				musics.add(music);
			}

			playlist.setMusics(musics);

			session.saveOrUpdate(playlist);
		}

	}

	private void registerMusics(User user) {

		for (int i = 0; i < 10; i++) {
			Music music = new Music();
			music.setArtist("artist" + i);
			music.setDescription("descrption" + i);
			music.setName("music" + i);

			Chord chord = new Chord();
			chord.setUrlChord("cifra1");
			Chord chord1 = new Chord();
			chord1.setUrlChord("cifra2");
			music.setChords(Arrays.asList(chord, chord1));
			music.setTags("test, test2, rock, pop, band" + i);
			music.setDateRecorded(new DateTime());
			music.setUser(user);

			session.save(music);
		}
	}

	private void registerUsers() {

		for (int i = 1; i < 5; i++) {

			User user1 = new User("login" + i);
			user1.setPassword("123");
			user1.setName("name" + 1);
			user1.setEmail(i + "lace@gma.com");
			session.save(user1);

		}
	}

	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();

		new Database(session, new MockValidator(), new MockResult()).buildBase();
		tx.commit();

		System.out.println("finish");

		System.exit(0);
	}
}