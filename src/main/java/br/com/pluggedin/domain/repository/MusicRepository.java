package br.com.pluggedin.domain.repository;

import java.util.List;
import br.com.pluggedin.domain.model.Music;

public interface MusicRepository {

	List<Music> listAllMusics();

	List<Music> listAllMusicsOfUser(String userLogin);

	List<Music> listMusicsOfUserOnDemand(int init, int max);
	
	List<Music> listTheLastMusicsAdded(int max);

	void saveMusic(Music music);

	List<Music> findMusics(String music);
	
	List<Music> findMusicsWithName(String name);
	
	List<Music> findMusicsFromArtist(String artist);

	

	
}