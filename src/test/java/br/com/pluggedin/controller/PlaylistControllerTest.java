package br.com.pluggedin.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.pluggedin.model.User;
import br.com.pluggedin.repository.MusicRepository;
import br.com.pluggedin.repository.UserRepository;
import br.com.pluggedin.test.AbstractTest;

public class PlaylistControllerTest extends AbstractTest {

	private PlaylistController	controller;

	@Mock
	private MusicRepository		musicRepo;
	@Mock
	private UserRepository		userRepo;

	@Before
	public void initTests() {

		controller = new PlaylistController(userRepo, result, validator, musicRepo);
	}

	@Test(expected = ValidationException.class)
	public void whenUserDoesntInformAnUserToSearch() {

		controller.list(null);
	}

	@Test(expected = ValidationException.class)
	public void whenUserDoesntInformAnUserNameToSearch() {

		controller.list(new User());
	}

	@Test
	public void whenUserInformAnNameThatReturnsMoreThanOneUser() {

		User user = new User();
		user.setName("rafa");
		when(userRepo.findUsers("rafa")).thenReturn(Arrays.asList(new User(), new User()));
		controller.list(user);
		verify(userRepo).findUsers("rafa");
		verify(musicRepo, never()).listAllMusicsOfUser("rafa");

		assertNull(result.included("musics"));
		assertNotNull(result.included("users"));
	}

	@Test
	public void whenUserInformAnNameThatReturnsJustOneUser() {

		User userSearched = new User();
		userSearched.setName("rafa");
		
		User userReturned = new User();
		userReturned.setName("rafa");
		userReturned.setLogin("rafalogin");
		
		when(userRepo.findUsers("rafa")).thenReturn(Arrays.asList(userReturned));

		controller.list(userSearched);

		verify(userRepo).findUsers("rafa");
		verify(musicRepo).listAllMusicsOfUser("rafalogin");

		assertNotNull(result.included("musics"));
		assertNull(result.included("users"));
	}

	@Test
	public void whenUserSelectsOneUserToSeeThePlaylist() {

		User user = new User();
		user.setLogin("rafalogin");
		user.setName("rafa");

		controller.list(user);

		verify(userRepo, never()).findUsers("rafa");
		verify(musicRepo).listAllMusicsOfUser("rafalogin");
		assertNotNull(result.included("musics"));
		assertNull(result.included("users"));
	}

	//test when find more than one user
	//test when search for the login already, it must make another query to search for the users
}
