package br.com.pluggedin.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import br.com.pluggedin.test.AbstractTest;

public class MusicTest extends AbstractTest {

	private Music	music;

	@Before
	public void initTests() {

		music = new Music();
	}

	@Test
	public void addValidTags() {

		assertEquals(0, music.getTags().size());
		music.setTags("jobs ,apple iwoz, pluggedin        ,project");
		assertEquals(4, music.getTags().size());
		assertEquals("pluggedin", music.getTags().get(2).getName());

	}

	@Test
	public void addInvalidTags() {

		assertEquals(0, music.getTags().size());
		music.setTags(null);
		assertEquals(0, music.getTags().size());

	}

}
