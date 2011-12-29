package br.com.pluggedin.secutiry;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.pluggedin.model.User;
import br.com.pluggedin.test.AbstractTest;

public class RestrictionInterceptorTest extends AbstractTest {

	private RestrictionInterceptor	interceptor;

	@Mock
	private InterceptorStack		stack;

	@Mock
	private ResourceMethod			method;

	@Before
	public void initTests() {

		interceptor = new RestrictionInterceptor(userLogged, result);
		when(method.getMethod()).thenReturn(mirror.on(this.getClass()).reflect().method("test").withoutArgs());
		assertTrue(interceptor.accepts(method));
		userLogged.getUser().addRole("admin");
	}

	@Test
	public void whenUserIsNotLoggedAndWantsToAccessRestrictResource() {

		assertNull(result.included("msg"));
		userLogged.logout();

		interceptor.intercept(stack, method, null);

		assertNotNull(result.included("msg"));
		verify(stack, never()).next(method, null);

	}

	@Test
	public void whenUserIsLoggedWantsToAccessRestrictResourceButDoesntHaveTheRole() {

		assertNull(result.included("msg"));
		userLogged.logout();
		userLogged.login(new User());

		interceptor.intercept(stack, method, null);

		assertNotNull(result.included("msg"));
		verify(stack, never()).next(method, null);

	}

	@Test
	public void whenUserIsLoggedAndWantsToAccessRestrictResource() {

		assertNull(result.included("msg"));

		interceptor.intercept(stack, method, null);

		assertNull(result.included("msg"));
		verify(stack).next(method, null);
	}

	@Restrict(values = { "admin" })
	public void test() {

	}
}
