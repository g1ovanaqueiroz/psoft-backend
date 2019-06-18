package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public interface UserDAO<T, ID> extends JpaRepository<User, String> {

	User save(User user);
	User findByEmail(String email);
}