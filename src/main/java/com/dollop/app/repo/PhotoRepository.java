package com.dollop.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dollop.app.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
	
	@Query("select p from Photo p where p.isActive = true")
	List<Photo> getAllActivePhotos();
}
