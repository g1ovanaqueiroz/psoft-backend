package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public interface UserDAO<T, ID> extends JpaRepository<User, String> {

	User save(User user);
	
	@Query(value="Select u from User u where u.email=:pemail")
	User findByEmail(@Param("pemail") String email);
}