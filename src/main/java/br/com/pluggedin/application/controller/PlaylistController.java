package br.com.pluggedin.application.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.pluggedin.domain.model.User;
import br.com.pluggedin.domain.repository.MusicRepository;
import br.com.pluggedin.domain.repository.UserRepository;

@Resource
public class PlaylistController {

	private final Result			result;
	private final Validator			validator;
	private final UserRepository	userRepo;
	private final MusicRepository	musicRepo;

	public PlaylistController(UserRepository userRepo, Result result, Validator validator, MusicRepository musicRepo) {

		this.userRepo = userRepo;
		this.result = result;
		this.validator = validator;
		this.musicRepo = musicRepo;
	}

	@Get
	@Path({ "/playlist/{user}","/playlist" })
	public User list(User user) {

		validate(user);

		if (isUserSelected(user)) {
			findMusics(user);
		} else {
			List<User> users = findPossibleUsers(user);
			if (users.size() == 1) {
				findMusics(users.get(0));
			} else {
				result.include("users", users);
			}
		}
		return user;

	}

	private List<User> findPossibleUsers(User user) {

		List<User> users = new ArrayList<User>();
		users = userRepo.findUsers(user.getName());
		return users;
	}

	private void findMusics(User user) {

		result.include("musics", musicRepo.listAllMusicsOfUser(user.getLogin()));
	}

	private boolean isUserSelected(User user) {

		return user.getLogin() != null && user.getName() != null;
	}

	private void validate(User user) {

		if (user == null || user.getName() == null) {
			validator.add(new ValidationMessage("invalid", "invalid_parameter"));
			validator.onErrorForwardTo(IndexController.class).home();
		}
	}
}
