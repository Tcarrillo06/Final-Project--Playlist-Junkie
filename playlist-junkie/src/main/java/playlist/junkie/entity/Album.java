package playlist.junkie.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Album {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long albumId;
	
	private String albumName;
	private String genre;
	
	//@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	//@OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
	//private Set<Song> songs = new HashSet<>();
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "artist_id")
	private Artist artist;
	

}
