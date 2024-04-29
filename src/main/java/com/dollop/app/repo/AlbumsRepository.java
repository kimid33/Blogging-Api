package com.dollop.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dollop.app.entity.Albums;

public interface AlbumsRepository extends JpaRepository<Albums, Integer> {
	
	@Query("select a from Albums a where a.isActive = true")
	List<Albums> getAllActiveAlbums();
}
