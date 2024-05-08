package playlist.junkie.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import playlist.junkie.entity.Album;

public interface AlbumDao extends JpaRepository<Album, Long> {

}
