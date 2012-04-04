package br.com.pluggedin.domain.repository;

import java.util.List;
import br.com.pluggedin.domain.model.Playlist;

public interface PlaylistRepository {

	List<Playlist> listAllPlaylistsOfUser(String login);

	List<Playlist> listAllPlaylists(String login);

}