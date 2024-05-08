package playlist.junkie.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playlistId;
	
	private String playlistName;
	private String description;
	private String dateCreated;
	
	
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	@ManyToMany(mappedBy = "playlists", cascade = CascadeType.PERSIST)
//	private Set<Song> songs = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "playlist_song", joinColumns = 
	@JoinColumn(name = "playlist_id"), inverseJoinColumns =
	@JoinColumn(name = "song_id", nullable = false))
	private Set<Song> songs = new HashSet<>();
	
}
