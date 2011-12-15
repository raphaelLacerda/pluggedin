package br.com.pluggedin;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class Teste implements TesteInterface {

	@Override
	public void hello() {

		System.out.println("primeiro teste");
	}
}
