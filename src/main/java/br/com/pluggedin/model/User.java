package br.com.pluggedin.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Entity
@Component
@SessionScoped
public class User {

	@Id
	@NotNull
	@Length(min = 3, max = 20)
	private String		login;

	@NotNull
	@Length(min = 3, max = 20)
	private String		password;

	@NotNull
	@Length(min = 3, max = 100)
	private String		name;

	@OneToMany
	@JoinColumn(name = "user")
	private Set<Music>	musics;

	public Set<Music> getMusics() {

		return musics;
	}

	public String getLogin() {

		return login;
	}

	public String getName() {

		return name;
	}

	public String getPassword() {

		return password;
	}

	@Override
	public String toString() {

		return "User [login=" + login + ", password=" + password + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

}