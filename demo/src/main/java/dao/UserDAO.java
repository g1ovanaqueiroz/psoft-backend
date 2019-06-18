package dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public interface UserDAO<T, ID extends Serializable> extends JpaRepository<User, Long> {

	User save(User user);
	User findById(String email);
}
