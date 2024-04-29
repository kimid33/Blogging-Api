package com.dollop.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dollop.app.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {
	
	@Query("select c from Comments c where c.isActive = true")
	List<Comments> getAllActiveByTrue();
}
 