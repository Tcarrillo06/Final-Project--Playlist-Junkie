package playlist.junkie.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import playlist.junkie.entity.Playlist;

@Data
@NoArgsConstructor
public class PlaylistData {

	private Long playlistId;
	private String playlistName;
	private String description;
	private String dateCreated;

	
	public PlaylistData(Playlist playlist) {
		playlistId = playlist.getPlaylistId();
		playlistName = playlist.getPlaylistName();
		description = playlist.getDescription();
		dateCreated = playlist.getDateCreated();
				
		//for (Song song : playlist.getSongs()) {
		//	songs.add(new SongData(song));
	
}
}

