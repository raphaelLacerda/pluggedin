package br.com.pluggedin.secutiry;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.pluggedin.component.UserLogged;
import br.com.pluggedin.controller.IndexController;

@Intercepts
@RequestScoped
public class RestrictionInterceptor extends AbstractInterceptor implements Interceptor {

	private final UserLogged	userLogged;
	private final Result		result;

	public RestrictionInterceptor(UserLogged userLogged, Result result) {

		this.userLogged = userLogged;
		this.result = result;

	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) throws InterceptionException {

		if (!userLogged.isLogged()) {
			result.include("msg", "You must be logged to access this resource");
			result.redirectTo(IndexController.class).home();
			return;
		}

		String[] roles = method.getMethod().getAnnotation(Restrict.class).values();

		for (int i = 0; i < roles.length; i++) {

			if (!userLogged.getUser().hasRole(roles[i])) {
				result.include("msg", "You must have the role " + roles[i]);
				result.redirectTo(IndexController.class).home();
				return;
			}
		}

		stack.next(method, resourceInstance);

	}

	@Override
	public boolean accepts(ResourceMethod method) {

		return method.getMethod().isAnnotationPresent(Restrict.class);
	}

}
