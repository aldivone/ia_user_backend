package br.com.dell.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// CrudRepository seria melhor? qual a diferença com o JpaRepository?
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByLogin(String login);
	
}
