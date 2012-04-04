package br.com.pluggedin.infra;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.pluggedin.domain.model.Playlist;
import br.com.pluggedin.domain.repository.AbstractRepository;
import br.com.pluggedin.domain.repository.PlaylistRepository;

public class PlaylistRepositoryDefault extends AbstractRepository implements PlaylistRepository {

	private final Session	session;
	private DAO<Playlist>	dao;

	public PlaylistRepositoryDefault(Session session) {

		super(session);
		this.session = session;
		this.dao = new DAO<Playlist>(Playlist.class, session);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Playlist> listAllPlaylistsOfUser(String login) {

		return session.createCriteria(getClassToSearch()).add(Restrictions.eq("user.login", login)).setCacheable(true).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Playlist> listAllPlaylists(String login) {

		return dao.list();
	}

	@Override
	protected Class<?> getClassToSearch() {

		return Playlist.class;
	}

}
