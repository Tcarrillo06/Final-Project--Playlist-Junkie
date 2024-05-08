package playlist.junkie.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import playlist.junkie.entity.Artist;

public interface ArtistDao extends JpaRepository<Artist, Long> {

}
