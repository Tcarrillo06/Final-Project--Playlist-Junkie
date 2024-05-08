package playlist.junkie.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import playlist.junkie.controller.model.AlbumData;
import playlist.junkie.controller.model.ArtistData;
import playlist.junkie.controller.model.PlaylistData;
import playlist.junkie.controller.model.SongData;
import playlist.junkie.controller.model.UserData;
import playlist.junkie.dao.AlbumDao;
import playlist.junkie.dao.ArtistDao;
import playlist.junkie.dao.PlaylistDao;
import playlist.junkie.dao.SongDao;
import playlist.junkie.dao.UserDao;
import playlist.junkie.entity.Album;
import playlist.junkie.entity.Artist;
import playlist.junkie.entity.Playlist;
import playlist.junkie.entity.Song;
import playlist.junkie.entity.User;

@Service
public class PlaylistService {

	@Autowired
	private SongDao songDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PlaylistDao playlistDao;
	
	@Autowired
	private ArtistDao artistDao;
	
	@Autowired
	private AlbumDao albumDao;
	
	
	
	//PLAYLIST
	
	@Transactional(readOnly = false)
	public PlaylistData savePlaylist(Long userId,PlaylistData playlistData) {
		
		User user = findUserById(userId);
		Long playlistId = playlistData.getPlaylistId();
		Playlist playlist = findOrCreatePlaylist(userId, playlistData.getPlaylistId());
		
		copyPlaylistFields(playlist, playlistData);
		playlist.setUser(user);
		user.getPlaylists().add(playlist);
		
		Playlist dbPlaylist = playlistDao.save(playlist);
		
		return new PlaylistData(dbPlaylist);
	}
	
	public void copyPlaylistFields(Playlist playlist, PlaylistData playlistData) {
		playlist.setPlaylistName(playlistData.getPlaylistName());
		playlist.setDescription(playlistData.getDescription());
		playlist.setDateCreated(playlistData.getDateCreated());
		
	}

	public Playlist findOrCreatePlaylist(Long userId,Long playlistId) {
		Playlist playlist;
		
		if(Objects.isNull(playlistId)) {
			playlist = new Playlist();
			
		}
		else {
			playlist = findPlaylistById(userId,playlistId);
		}
		
		return playlist;
	}

	public Playlist findPlaylistById(Long userId, Long playlistId) {
		return playlistDao.findById(playlistId).orElseThrow(() -> new NoSuchElementException( "Playlist with ID=" + playlistId + " was not found."));
	
	}
	
	public Playlist findPlaylistById(Long playlistId) {
		return playlistDao.findById(playlistId).orElseThrow(() -> new NoSuchElementException( "Playlist with ID=" + playlistId + " was not found."));
	}
	
	public PlaylistData retrievePlaylistById(Long playlistId) {
		return new PlaylistData(findPlaylistById(playlistId));
	}

	public void deletePlaylistById(Long playlistId) {
		Playlist playlist  = findPlaylistById(playlistId);
		playlistDao.delete(playlist);
		
	}
	
	
	
	
	
	//SONG
	
	@Transactional(readOnly = false)
	public SongData saveSong(Long playlistId,SongData songData) {
		
		Playlist playlist = findPlaylistById(playlistId);
		Long songId = songData.getSongId();
		Song song = findOrCreateSong(playlistId, songData.getSongId());
		
		copySongFields(song, songData);
		song.getPlaylists().add(playlist);
		playlist.getSongs().add(song);
		
		Song dbSong = songDao.save(song);
		
		return new SongData(dbSong);
	}


	public void copySongFields(Song song, SongData songData) {
		song.setTitle(songData.getTitle());
		song.setGenre(songData.getGenre());
		
		
	}


	public Song findOrCreateSong(Long playlistId, Long songId) {
		
		if(Objects.isNull(songId)) {
		return new Song();
			
		}
		return findSongById(songId, playlistId);
		
	}

	public Song findSongById(Long songId, Long playlistId) {
		Song song = songDao.findById(songId).orElseThrow(() -> new NoSuchElementException("Song with ID=" + songId + " was not found."));
		
		boolean found = false;
		
		for(Playlist playlist: song.getPlaylists()) {
			if(playlist.getPlaylistId() == playlistId) {
				found = true;
				break;		
		}
			}
		if(!found) {
			throw new IllegalArgumentException("The song with ID=" + songId + " is not a part of playlist with ID=" + playlistId + ".");
		}
		
		return song;
	}	
	
	
	public SongData retrieveSongById(Long songId) {
		return new SongData(findSongById(songId));
	}

	@Transactional(readOnly = false)
	public void deleteSongById(Long songId) {
		Song song  = findSongById(songId);
		songDao.delete(song);
		
	}
	
	
	
	
	
	
	//USER
	
	@Transactional(readOnly = false)
	public UserData saveUser(UserData userData) {
		Long userId = userData.getUserId();
		User user = findOrCreateUser(userId);
		
		setFieldsInUser(user, userData);
		return new UserData(userDao.save(user));


	}

	@Transactional(readOnly = false)
	public User findOrCreateUser(Long userId) {
		User user;
		
		if(Objects.isNull(userId)) {
			user = new User();
			
		}
		else {
			user = findUserById(userId);
		}
		
		return user;
		
	}
		
	@Transactional(readOnly = false)
	public User findUserById(Long userId) {
		return userDao.findById(userId).orElseThrow(() -> new NoSuchElementException( "User with ID=" + userId + " was not found."));
	}

	public void setFieldsInUser(User user, UserData userData) {
		user.setEmail(userData.getEmail());
		user.setFirstName(userData.getFirstName());
		user.setLastName(userData.getLastName());
		user.setUsername(userData.getUsername());
			
	}
	@Transactional(readOnly = false)
	public UserData retrieveUserById(Long userId) {
		return new UserData(findUserById(userId));
	}

	@Transactional(readOnly = false)
	public void deleteUserById(Long userId) {
		User user  = findUserById(userId);
		userDao.delete(user);
		
	}
	@Transactional(readOnly = false)
	public PlaylistData retrievePlaylistById(Long userId, Long playlistId) {
		Playlist playlist = findPlaylistById(userId,playlistId);
		return new PlaylistData(playlist);
	}
	@Transactional(readOnly = false)
	public void deletePlaylistById(Long userId,Long playlistId) {
		Playlist playlist = findPlaylistById(userId,playlistId);
		playlistDao.delete(playlist);
	}
	
	
	
	
	
	//ARTIST
	
	@Transactional
	public ArtistData saveArtist(Long playlistId,ArtistData artistData,Long songId ) {
		Song song = findSongById(playlistId,songId);
		Long artistId = artistData.getArtistId();
		Artist artist = findOrCreateArtist(songId,artistId);
		
		copyArtistFields(artist, artistData);
		
		artist.getSongs().add(song);
		song.setArtist(artist);
		
		Artist dbArtist = artistDao.save(artist);		
		
		return new ArtistData(dbArtist);
	}

	private void copyArtistFields(Artist artist, ArtistData artistData) {
		artist.setArtistId(artistData.getArtistId());
		artist.setGenre(artistData.getGenre());
		artist.setName(artistData.getName());
		
	}

	private Artist findOrCreateArtist(Long songId, Long artistId) {
		if(Objects.isNull(artistId)) {
			return new Artist();
				
			}
			return findArtistById(songId, artistId);
			
		}
	@Transactional(readOnly = false)
	private Artist findArtistById(Long songId, Long artistId) {
		Artist artist = artistDao.findById(artistId).orElseThrow(() -> new NoSuchElementException("Artist with ID=" + artistId + " was not found."));
		
		boolean found = false;
		
		for(Song song : artist.getSongs()) {
			if(song.getSongId() == songId) {
				found = true;
				break;
			}
		}
		
		if(!found) {
			throw new IllegalArgumentException("The Artist with ID=" + artistId + " is not part of song ID=" + songId + ".");
			
	}
		return artist;
		
	}

	public ArtistData saveArtist(ArtistData artistData, Long songId) {
		Song song = findSongById(songId);
		Long artistId = artistData.getArtistId();
		Artist artist = findOrCreateArtist(songId,artistId);
		
		copyArtistFields(artist, artistData);
		
		artist.getSongs().add(song);
		song.setArtist(artist);
		
	
			
		Artist dbArtist = artistDao.save(artist);		
			
		return new ArtistData(dbArtist);
		}
	
	private Artist findArtistById(Long artistId) {
		Artist artist = artistDao.findById(artistId).orElseThrow(() -> new NoSuchElementException("Artist with ID=" + artistId + " was not found."));
		
		
		return artist;
	}
		
		
	
	private Song findSongById(Long songId) {
		Song song = songDao.findById(songId).orElseThrow(() -> new NoSuchElementException("Song with ID=" + songId + " was not found."));
		
	
		return song;
	}
	
	@Transactional(readOnly = false)
	public ArtistData retrieveArtistById(Long artistId) {
		return new ArtistData(findArtistById(artistId));
	}

	
	
	
	
	
	//ALBUM
	
	public AlbumData saveAlbum(AlbumData albumData, Long artistId) {
		Artist artist = findArtistById(artistId);
		Long albumId = albumData.getAlbumId();
		Album album = findOrCreateAlbum(artistId,albumId);
		
		copyAlbumFields(album, albumData);
		
		album.setArtist(artist);
		artist.getAlbums().add(album);
		
	
			
		Album dbAlbum = albumDao.save(album);		
			
		return new AlbumData(dbAlbum);
		
	}

	private void copyAlbumFields(Album album, AlbumData albumData) {
		album.setAlbumId(albumData.getAlbumId());
		album.setAlbumName(albumData.getAlbumName());
		album.setGenre(albumData.getGenre());
		
	}

	private Album findOrCreateAlbum(Long artistId, Long albumId) {
		if(Objects.isNull(albumId)) {
			return new Album();
				
			}
			return findAlbumById(albumId);
			
	}

	private Album findAlbumById(Long albumId) {
		Album album = albumDao.findById(albumId).orElseThrow(() -> new NoSuchElementException("Album with ID=" + albumId + " was not found."));
		
		
		return album;
	}

	
	@Transactional(readOnly = false)
	public AlbumData retrieveAlbumById(Long albumId) {
		return new AlbumData(findAlbumById(albumId));
	}	
	
	
	
}
