package br.com.pluggedin;

import br.com.caelum.vraptor.ioc.Component;




@Component
public class Teste2 implements TesteInterface {

	
	/* (non-Javadoc)
	 * @see br.com.pluggedin.TesteInterface#hello()
	 */
	@Override
	public void hello() {
		System.out.println("segundo teste");
	}
}
