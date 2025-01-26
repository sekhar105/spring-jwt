package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

//	 @Query(value = "select * from users where email=?1", nativeQuery = true)
//	    Optional<User> findByEmail(String username);

	 Optional<User> findByUsername(String username);
	   
}
