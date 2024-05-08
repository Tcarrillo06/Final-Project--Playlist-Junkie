package playlist.junkie.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import playlist.junkie.entity.Song;

public interface SongDao extends JpaRepository<Song, Long> {

}
