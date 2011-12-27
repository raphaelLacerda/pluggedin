package br.com.pluggedin.controller;

import java.util.HashSet;
import java.util.List;
import org.joda.time.DateTime;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.serialization.JSONSerialization;
import br.com.caelum.vraptor.serialization.XMLSerialization;
import br.com.pluggedin.component.UserLogged;
import br.com.pluggedin.model.Music;
import br.com.pluggedin.repository.MusicRepository;

@Resource
public class MusicController {

	private final UserLogged		userLogged;
	private final MusicRepository	musicRepo;
	private final Result			result;
	private final Validator			validator;

	public MusicController(UserLogged userLogged, MusicRepository musicRepo, Result result, Validator validator) {

		this.userLogged = userLogged;
		this.musicRepo = musicRepo;
		this.result = result;
		this.validator = validator;

	}

	@Get
	@Path({ "/music/search/{music}", "/music/search" })
	public void search(String music) {

		result.include("musics", new HashSet<Music>(getMusicsWith(music)));
	}

	@Path({ "/musics", "/music/list" })
	public List<Music> list() {

		List<Music> allMusicsOfUser = getMusicsOfUser(userLogged.getUser().getLogin());
		if (allMusicsOfUser.size() > 5) {

			List<Music> lastFiveMusics = allMusicsOfUser.subList(0, 5);
			result.include("lastFiveMusics", lastFiveMusics);
		}
		return allMusicsOfUser;

	}

	@Post
	public void save(Music music, String tags) {

		if (music == null) {
			result.redirectTo(this).list();
			throw new IllegalArgumentException("Music is invalid");
		}
		music.setTags(tags);
		music.setUser(userLogged.getUser());
		validar(music);
		music.setDateRecorded(new DateTime());
		musicRepo.saveMusic(music);
		result.redirectTo(this).list();
	}

	@Get
	@Path({ "/musics/json", "/musics/json/{music}" })
	public void listJson(String music) {

		List<Music> musics = getMusicsWith(music);
		if (musics.size() > 0) {
			result.use(JSONSerialization.class).withoutRoot().from(musics).exclude("id").serialize();
		}
	}

	@Get
	@Path({ "/musics/xml", "/musics/xml/{music}" })
	public void listXML(String music) {

		List<Music> musics = getMusicsWith(music);
		if (musics.size() > 0) {
			result.use(XMLSerialization.class).from(musics).exclude("id").serialize();
		}

	}

	private void validar(Music music) {

		validator.validate(music);
		validator.onErrorUsePageOf(this).list();
	}

	private List<Music> getMusicsOfUser(String userLogin) {

		List<Music> allMusicsOfUser = musicRepo.listAllMusicsOfUser(userLogin);
		return allMusicsOfUser;
	}

	private List<Music> getMusicsWith(String music) {

		List<Music> musics;
		if (music == null) {
			musics = musicRepo.listAllMusics();
		} else {
			musics = musicRepo.findMusics(music);
		}
		return musics;
	}
}
