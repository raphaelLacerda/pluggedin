package br.com.pluggedin.component;

import java.util.Arrays;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.joda.time.DateTime;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.pluggedin.controller.UserController;
import br.com.pluggedin.model.Chord;
import br.com.pluggedin.model.Music;
import br.com.pluggedin.model.User;
import br.com.pluggedin.secutiry.Restrict;

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

		for (int i = 1; i < 5; i++) {

			User user1 = new User("login" + i);
			user1.setPassword("123");
			user1.setName("name" + 1);
			user1.setEmail(i + "lace@gma.com");
			session.save(user1);

		}

		User user = new User("login1");
		for (int i = 0; i < 10; i++) {
			Music music = new Music();
			music.setArtist("artist" + i);
			music.setDescription("descrption" + i);
			music.setName("music" + i);

			music.setChords(Arrays.asList(new Chord()));
			music.setTags("teste," + i);
			music.setDateRecorded(new DateTime());
			music.setUser(user);

			session.save(music);
		}

		result.redirectTo(UserController.class).list();
	}

}