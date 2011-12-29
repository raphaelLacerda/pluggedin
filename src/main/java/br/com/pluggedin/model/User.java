package br.com.pluggedin.model;

import static ch.lambdaj.Lambda.select;
import static org.hamcrest.Matchers.equalTo;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Entity
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User {

	@Id
	@NotNull
	@Length(min = 3, max = 20)
	private String			login;

	@NotNull
	@Length(min = 3, max = 20)
	private String			password;

	@NotNull
	@Length(min = 3, max = 100)
	@Field(index = Index.TOKENIZED)
	private String			name;

	@Transient
	private List<String>	roles;

	@NotNull
	@Email
	private String			email;

	public User() {

	}

	public User(String login) {

		this.login = login;
	}

	public void addRole(String... roles) {

		this.roles = Arrays.asList(roles);
	}

	public boolean hasRole(String role) {

		return select(roles, equalTo(role)).size() > 0;
	}

	public void setLogin(String login) {

		this.login = login;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public void setName(String name) {

		this.name = name;
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

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
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