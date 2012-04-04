package br.com.pluggedin.domain.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Indexed
public class Playlist {

	@Id
	@GeneratedValue
	private long		id;

	@NotNull
	@Field(index = Index.TOKENIZED)
	@Length(min = 3, max = 20)
	private String		name;

	@Length(min = 3, max = 100)
	@Field(index = Index.TOKENIZED)
	private String		description;

	@Length(min = 3, max = 20)
	private String		category;

	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime	dateRecorded;

	@ManyToMany
	@Cascade(value = { CascadeType.SAVE_UPDATE })
	@JoinTable(joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns = @JoinColumn(name = "music_id"))
	private List<Music>	musics	= new ArrayList<Music>();

	@NotNull
	@ManyToOne
	private User		user;

	@PrePersist
	public void setUp() {

		this.dateRecorded = new DateTime();
	}

	public User getUser() {

		return user;
	}

	public void setName(String name) {

		this.name = name;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public void setDateRecorded(DateTime dateRecorded) {

		this.dateRecorded = dateRecorded;
	}

	public void setUser(User user) {

		this.user = user;
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

	public DateTime getDateRecorded() {

		return dateRecorded;
	}

	public List<Music> getMusics() {

		return musics;
	}

	public void setMusics(List<Music> musics) {

		this.musics = musics;
	}

	public String getCategory() {

		return category;
	}

	public void setCategory(String category) {

		this.category = category;
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
		Playlist other = (Playlist) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "Playlist [id=" + id + ", name=" + name + ", description=" + description + ", dateRecorded=" + dateRecorded + "]";
	}

}