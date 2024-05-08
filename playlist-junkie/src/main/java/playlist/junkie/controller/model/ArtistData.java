package playlist.junkie.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import playlist.junkie.entity.Album;
import playlist.junkie.entity.Artist;
import playlist.junkie.entity.Song;

@Data
@NoArgsConstructor
public class ArtistData {
	
	private Long artistId;
	private String name;
	private String genre;
	private Set<SongData> songs = new HashSet<>();
	private Set<AlbumData> albums = new HashSet<>();
	
		public ArtistData(Artist artist) {
		artistId = artist.getArtistId();
		name = artist.getName();
		genre = artist.getGenre();
		
		for (Song song : artist.getSongs()) {
			songs.add(new SongData(song));
	}
		for (Album album : artist.getAlbums()) {
			albums.add(new AlbumData(album));
		}
		
	}
}
