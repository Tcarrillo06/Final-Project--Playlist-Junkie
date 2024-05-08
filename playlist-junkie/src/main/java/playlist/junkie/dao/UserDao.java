package playlist.junkie.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import playlist.junkie.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

}
