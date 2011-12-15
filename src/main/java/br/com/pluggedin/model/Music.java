package br.com.pluggedin.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Music {

	@Id
	private long	id;
	private String	name;
	private String	description;
	private String	artist;
	private String	urlChord;

	public String getUrlChord() {

		return urlChord;
	}

	public long getId() {

		return id;
	}

	public String getName() {

		return name;
	}

	public String getDescription() {

		return description;
	}

	public String getArtist() {

		return artist;
	}

}
