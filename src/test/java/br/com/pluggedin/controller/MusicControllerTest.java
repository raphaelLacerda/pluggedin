package br.com.pluggedin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import br.com.pluggedin.application.controller.MusicController;
import br.com.pluggedin.domain.model.Music;
import br.com.pluggedin.domain.repository.MusicRepository;
import br.com.pluggedin.test.AbstractTest;

public class MusicControllerTest extends AbstractTest {

	private MusicController	controller;
	@Mock
	private MusicRepository	musicRepo;

	@Before
	public void initTests() {

		controller = new MusicController(userLogged, musicRepo, result, validator);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void listMusicswhenUserHasMoreThanFive() {

		List<Music> musics = Arrays.asList(new Music(), new Music(), new Music(), new Music(), new Music(),
				new Music(), new Music());
		when(musicRepo.listAllMusicsOfUser("rafa")).thenReturn(musics);

		List<Music> allMusics = controller.list();

		assertEquals(5, ((List) result.included("musics")).size());
		assertEquals(7, allMusics.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void listMusicswhenUserDoesntHaveMoreThanFive() {

		List<Music> musics = Arrays.asList(new Music(), new Music());
		when(musicRepo.listAllMusicsOfUser("rafa")).thenReturn(musics);

		List<Music> allMusics = controller.list();

		assertEquals(2, ((List<Music>) result.included("musics")).size());
		assertEquals(2, allMusics.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void listMusicsWhenUserIsNotLoggedIn() {

		userLogged.logout();
		List<Music> musics = Arrays.asList(new Music(), new Music(), new Music(), new Music(), new Music(),
				new Music(), new Music());
		when(musicRepo.listAllMusics()).thenReturn(musics);

		List<Music> allMusics = controller.list();

		assertEquals(7, ((List<Music>) result.included("musics")).size());
		assertEquals(7, allMusics.size());
	}

	@Test
	public void saveValidMusic() {

		Music music = new Music();
		music.setName("music test name");
		music.setArtist("music artist");

		assertNull(music.getDateRecorded());
		assertNull(music.getUser());

		controller.save(music, "pluggedin, music, test");

		assertNotNull(music.getDateRecorded());
		assertEquals(userLogged.getUser(), music.getUser());
		assertEquals(3, music.getTags().size());
		verify(musicRepo, times(1)).saveMusic(music);
	}

	@Test(expected = IllegalArgumentException.class)
	public void saveInvalidMusic() {

		controller.save(null, "pluggedin, music ,test");

	}
}
