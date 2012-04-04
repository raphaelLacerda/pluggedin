/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.pluggedin.application.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.pluggedin.domain.model.Music;

@Resource
public class TestController {

	private final Result		result;

	private static final Logger	logger	= LoggerFactory.getLogger(TestController.class);

	public TestController(Result result) {

		this.result = result;

	}

	@Path("test")
	public String test() {

		result.include("hello", "requestInclude");
		return "hello REquest";
	}

	public void test2() {

	}

	public void save(List<Music> musics) {

		System.out.println(musics);
		result.redirectTo(this).test();

	}

//	public void save(Music musics) {
//
//		System.out.println(musics);
//		result.redirectTo(this).test();
//
//	}

	@Get
	@Path({ "test/chords/json", "test/chords/json/{chord}" })
	public void listChords(String chord) {

		Music music1 = new Music();
		music1.setName("teste1");
		Music music2 = new Music();
		music2.setName("teste2");
		Music music3 = new Music();
		music3.setName("teste3");
		List<Music> musics = new ArrayList<Music>();
		musics.addAll(Arrays.asList(music1, music2, music3));
		logger.debug("recebido request {}", chord);
		result.use(Results.json()).withoutRoot().from(musics).exclude("id").serialize();
	}
}
