package com.dollop.app.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dollop.app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.isActive = true")
	List<User> findByIsActiveTrue();
	
	@Query("select u from User u where u.isActive = true and u.userName = :username")
	List<User> findByuserNameContaining(@Param("username") String keyword);
	
	@Query("select u from User u where u.userPassword = :password and u.userName = :username")
	User findByuserNameAndPassword(@Param("username") String name,@Param("password") String pass);

//	User findByUserNameAndUserPassword(String userName, String userPassword);

//	@Query("select u from User u where u.userName = :username")
	boolean existsByuserName(String userName);
	boolean existsByuserEmail(String userEmail);
	public boolean existsByuserWebsite(String userWebsite);
	public boolean existsByuserPhone(String userPhone);

	public boolean existsByuserPassword(String userPassword);
	
	Optional<User> findByuserName(String userName);

	Optional<User> findByuserEmail(String userEmail);
}
