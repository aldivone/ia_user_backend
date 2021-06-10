package br.com.dell.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// CrudRepository seria melhor? qual a diferenša com o JpaRepository?
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
