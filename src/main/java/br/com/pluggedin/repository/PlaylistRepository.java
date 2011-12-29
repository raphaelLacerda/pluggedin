package br.com.pluggedin.repository;

import java.util.List;
import br.com.pluggedin.model.Playlist;

public interface PlaylistRepository {

	List<Playlist> listAllPlaylistsOfUser(String login);

	List<Playlist> listAllPlaylists(String login);

}