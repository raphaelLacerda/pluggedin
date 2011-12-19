package br.com.pluggedin.component;

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

		User user = new User();
		user.setLogin("login");
		user.setPassword("123");
		user.setName("name");
		session.save(user);

		for (int i = 0; i < 10; i++) {
			Music music = new Music();
			music.setArtist("artist" + i);
			music.setDescription("descrption" + i);
			music.setName("music" + i);
			music.setUrlChord("chord" + i);
			music.setDateRecorded(new DateTime());
			music.addUser(user);
			
			session.save(music);
		}

		result.redirectTo(UserController.class).list();
	}

}
