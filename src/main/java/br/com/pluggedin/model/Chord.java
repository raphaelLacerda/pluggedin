package br.com.pluggedin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Chord {

	@Id
	@GeneratedValue
	private long	id;
	private String	urlChord;

	public long getId() {

		return id;
	}

	public void setId(long id) {

		this.id = id;
	}

	public String getUrlChord() {

		return urlChord;
	}

	public void setUrlChord(String urlChord) {

		this.urlChord = urlChord;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chord other = (Chord) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "Chord [id=" + id + ", urlChord=" + urlChord + "]";
	}

}
