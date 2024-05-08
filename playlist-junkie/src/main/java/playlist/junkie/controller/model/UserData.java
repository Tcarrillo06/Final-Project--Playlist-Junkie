package playlist.junkie.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import playlist.junkie.entity.Playlist;
import playlist.junkie.entity.User;

@Data
@NoArgsConstructor
public class UserData {

	
	private Long userId;	
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private Set<PlaylistData> playlists = new HashSet<>();
	
	
	public UserData(User user) {
		userId = user.getUserId();
		username = user.getUsername();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		email = user.getEmail();
		
		for (Playlist playlist : user.getPlaylists()) {
			playlists.add(new PlaylistData(playlist));
		}
		
	}
}

