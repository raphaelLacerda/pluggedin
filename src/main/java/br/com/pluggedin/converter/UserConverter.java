package br.com.pluggedin.converter;

import java.util.ResourceBundle;
import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.pluggedin.model.User;

@Convert(User.class)
@RequestScoped
public class UserConverter implements Converter<User> {

	@Override
	public User convert(String value, Class<? extends User> type, ResourceBundle bundle) {

		User user = new User(value);
		user.setName("");
		return user;
	}

}
