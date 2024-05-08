package playlist.junkie.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import playlist.junkie.entity.Playlist;

public interface PlaylistDao extends JpaRepository<Playlist, Long> {

}
