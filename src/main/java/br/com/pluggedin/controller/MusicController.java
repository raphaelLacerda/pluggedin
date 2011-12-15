package br.com.pluggedin.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class MusicController {

	@Path({ "/musicas", "/music/list" })
	public void list() {

	}
}
