package playlist.junkie.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import playlist.junkie.entity.Playlist;
import playlist.junkie.entity.Song;

@Data
@NoArgsConstructor
public class SongData {

	private Long songId;
	private String title;
	private String genre;
	private Set<PlaylistData> playlists = new HashSet<>();
	
	
	public SongData(Song song) {
		songId = song.getSongId();
		title = song.getTitle();
		genre = song.getGenre();
		
		for (Playlist playlist : song.getPlaylists()) {
			playlists.add(new PlaylistData(playlist));
		}
	}

			
	}

