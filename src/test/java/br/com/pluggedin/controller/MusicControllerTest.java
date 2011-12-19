package br.com.pluggedin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import br.com.pluggedin.model.Music;
import br.com.pluggedin.repository.MusicRepository;
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

		List<Music> musics = Arrays.asList(new Music(), new Music(), new Music(), new Music(), new Music(), new Music(), new Music());
		when(musicRepo.listAllMusicsOfUser("rafa")).thenReturn(musics);
		
		List<Music> allMusics = controller.list();
	
		assertEquals(5, ((List) result.included("lastFiveMusics")).size());
		assertEquals(7, allMusics.size());
	}

	@Test
	public void listMusicswhenUserDoesntHaveMoreThanFive() {

		List<Music> musics = Arrays.asList(new Music(), new Music());
		when(musicRepo.listAllMusicsOfUser("rafa")).thenReturn(musics);
		
		List<Music> allMusics = controller.list();
		
		assertNull(result.included("lastFiveMusics"));
		assertEquals(2, allMusics.size());
	}
}
