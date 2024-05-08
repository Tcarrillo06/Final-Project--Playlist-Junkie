package playlist.junkie.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long songId;
	
	private String title;
	private String genre;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "songs", cascade = CascadeType.PERSIST)
	private Set<Playlist> playlists = new HashSet<>();
	
	
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	@ManyToMany(cascade = CascadeType.PERSIST)
//	@JoinTable(name = "playlist_song", joinColumns = 
//	@JoinColumn(name = "song_id"), inverseJoinColumns =
//	@JoinColumn(name = "playlist_id", nullable = false))
//	private Set<Playlist> playlists = new HashSet<>();
	
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	//@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "album_id")
	//private Album album;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "artist_id")
	private Artist artist;





	

	
	
}
