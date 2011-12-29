package br.com.pluggedin.test;

import net.vidageek.mirror.dsl.Mirror;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.pluggedin.component.UserLogged;
import br.com.pluggedin.model.User;

public class AbstractTest {

	protected MockResult	result;
	protected MockValidator	validator;
	protected UserLogged	userLogged;
	protected Mirror		mirror;

	@Before
	public final void initAbstractTest() {

		MockitoAnnotations.initMocks(this);

		mirror = new Mirror();
		result = new MockResult();
		validator = new MockValidator();
		userLogged = new UserLogged();
		User user = new User("rafa");
		user.setPassword("123");
		userLogged.login(user);

	}

}
