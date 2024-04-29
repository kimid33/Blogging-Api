package com.dollop.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dollop.app.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
//	@Query("select p from Post p where p.isActive=true")
//	List<Post> findByIsActiveTrue();
	
	@Query("SELECT p From Post p WHERE p.isActive=true")
	List<Post> getAllActivePost();
}
