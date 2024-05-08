package playlist.junkie.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import playlist.junkie.entity.Album;

@Data
@NoArgsConstructor
public class AlbumData {

	
	private Long albumId;
	private String albumName;
	private String genre;
	//private Set<SongData> songs = new HashSet<>();
	
	 public AlbumData(Album album) {
	 albumId = album.getAlbumId();
	 albumName = album.getAlbumName();
	 genre = album.getGenre();
	
	//for (Song song : album.getSongs()) {
	//	songs.add(new SongData(song));

		
		
	}
	
	
}
