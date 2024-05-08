package playlist.junkie.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import playlist.junkie.controller.model.AlbumData;
import playlist.junkie.controller.model.ArtistData;
import playlist.junkie.controller.model.PlaylistData;
import playlist.junkie.controller.model.SongData;
import playlist.junkie.controller.model.UserData;
import playlist.junkie.service.PlaylistService;

@RestController
@RequestMapping("/playlist_junkie")
@Slf4j
public class PlaylistController {
	
	@Autowired
	private PlaylistService playlistService;
	
	
	
	//SONG
	
	@PostMapping("/playlist/{playlistId}/song")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SongData insertSong(@PathVariable Long playlistId, @RequestBody SongData songData ) {

		log.info("Creating Song {} with Playlist ID={}",playlistId, songData);
		return playlistService.saveSong(playlistId,songData);	
		
	
	}

	@DeleteMapping("/song/{songId}")
	public Map<String, String> deleteSongById(@PathVariable Long songId) {
			

		log.info("Deleting song with ID={}", songId);
		playlistService.deleteSongById(songId);
		
		return Map.of("message", "Deletion of song with ID=" + songId 
				+ " was successful.");
	}
	
	@GetMapping("/song/{songId}")
	public SongData retrieveSongById(@PathVariable Long songId) {

		log.info("Retrieving song by ID={}",songId);
		return playlistService.retrieveSongById(songId);
	}
	
	
	
	//PLAYLIST
		
	@PostMapping("{userId}/playlist")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PlaylistData insertPlaylist(@PathVariable Long userId,@RequestBody PlaylistData playlistData ) {

		log.info("Creating Playlist {} with User ID={}",userId, playlistData);
		return playlistService.savePlaylist(userId,playlistData);
	
	}
	
	@PutMapping("/playlist/{playlistId}")
	public PlaylistData updatePlaylist(@PathVariable Long playlistId, @RequestBody PlaylistData playlistData) {
		playlistData.setPlaylistId(playlistId);
		
		log.info("Updating Playlist {}", playlistData);
		return playlistService.savePlaylist(playlistId,playlistData);
	
	}	
	
	@GetMapping("/playlist/{playlistId}")
	public PlaylistData retrievePlaylistById(@PathVariable Long playlistId) {

		log.info("Retrieving Playlist by ID={}",playlistId);
		return playlistService.retrievePlaylistById(playlistId);
	}
	
	@DeleteMapping("/playlist/{playlistId}")
	public Map<String, String> deletePlaylistById(@PathVariable Long playlistId) {
			

		log.info("Deleting Playlist with ID={}", playlistId);
		playlistService.deletePlaylistById(playlistId);
		
		return Map.of("message", "Deletion of Playlist with ID=" + playlistId 
				+ " was successful.");
	}
	
	
	//USER
	
	@PostMapping("/user")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserData insertUser(@RequestBody UserData userData) {
		
		log.info("Creating user {}", userData);
		return playlistService.saveUser(userData);
		
	}
	 
	@PutMapping("/{userId}")
	public UserData updateUser(@PathVariable Long userId, @RequestBody UserData userData) {
		userData.setUserId(userId);
		
		log.info("Updating User {}", userData);
		return playlistService.saveUser(userData);
	
	}	
	
	@GetMapping("/{userId}")
	public UserData retrieveUserById(@PathVariable Long userId) {

		log.info("Retrieving user by ID={}", userId);
		return playlistService.retrieveUserById(userId);
	}
	
	@DeleteMapping("/{userId}")
	public Map<String, String> deleteuserById(@PathVariable Long userId) {
			

		log.info("Deleting User with ID={}", userId);
		playlistService.deleteUserById(userId);
		
		return Map.of("message", "Deletion of User with ID=" + userId 
				+ " was successful.");
	}
	
	
	// ARTIST
	
	@PostMapping("/song/{songId}/artist")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistData addArtistToSong(@PathVariable Long songId, @RequestBody ArtistData artistData) {
		log.info("Adding artist {} to song with ID={}", artistData,songId);
		
		return playlistService.saveArtist(artistData,songId);
	}
	
	@GetMapping("/artist/{artistId}")
	public ArtistData retrieveArtistById(@PathVariable Long artistId) {

		log.info("Retrieving artist by ID={}", artistId);
		return playlistService.retrieveArtistById(artistId);
	}
	

	// ALBUM
	
	@PostMapping("/artist/{artistId}/album")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AlbumData addAlbumToArtist(@PathVariable Long artistId, @RequestBody AlbumData albumData) {
		log.info("Adding album {} to artist with ID={}", albumData,artistId);
		
		return playlistService.saveAlbum(albumData,artistId);
	}
	@GetMapping("/album/{albumId}")
	public AlbumData retrieveAlbumById(@PathVariable Long albumId) {

		log.info("Retrieving album by ID={}", albumId);
		return playlistService.retrieveAlbumById(albumId);
	}
}