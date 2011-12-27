package br.com.pluggedin.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

@Entity
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Music {

	@Id
	@GeneratedValue
	private long		id;
	@Field(index = Index.TOKENIZED)
	@NotNull
	@Length(min = 2, max = 20)
	private String		name;
	@Field(index = Index.TOKENIZED)
	@Length(min = 3, max = 100)
	private String		description;
	@Field(index = Index.TOKENIZED)
	@NotNull
	@Length(min = 3, max = 20)
	private String		artist;
	private String		urlYoutubeVideo;

	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime	dateRecorded;

	@NotNull
	@ManyToOne
	private User		user;

	@ManyToMany()
	@Cascade(value = { CascadeType.SAVE_UPDATE })
	@JoinTable(joinColumns = @JoinColumn(name = "music_id"), inverseJoinColumns = @JoinColumn(name = "chord_id"))
	private List<Chord>	chords	= new ArrayList<Chord>();

	@ManyToMany()
	@Cascade(value = { CascadeType.SAVE_UPDATE })
	@JoinTable(joinColumns = @JoinColumn(name = "music_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	@IndexedEmbedded
	private List<Tag>	tags	= new ArrayList<Tag>();

	public void setTags(String tags) {

		if (tags == null) {
			return;
		}
		String[] tagsToSave = tags.split(",");
		for (String nameTag : tagsToSave) {
			Tag tag = new Tag(nameTag.trim());
			this.tags.add(tag);
		}

	}

	public void setUser(User user) {

		this.user = user;
	}

	public List<Tag> getTags() {

		return tags;
	}

	public void setDateRecorded(DateTime dateRecorded) {

		this.dateRecorded = dateRecorded;
	}

	public DateTime getDateRecorded() {

		return dateRecorded;
	}

	public User getUser() {

		return user;
	}

	public String getUrlYoutubeVideo() {

		return urlYoutubeVideo;
	}

	public void setUrlYoutubeVideo(String urlYoutubeVideo) {

		this.urlYoutubeVideo = urlYoutubeVideo;
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

	public String getArtist() {

		return artist;
	}

	public void setName(String name) {

		this.name = name;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public void setArtist(String artist) {

		this.artist = artist;
	}

	public List<Chord> getChords() {

		return chords;
	}

	public void setChords(List<Chord> chords) {

		this.chords = chords;
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
		Music other = (Music) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "Music [id=" + id + ", name=" + name + ", description=" + description + ", artist=" + artist + ", urlYoutubeVideo=" + urlYoutubeVideo
				+ ", dateRecorded=" + dateRecorded + "]";
	}
}
