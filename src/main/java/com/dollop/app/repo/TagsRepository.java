package com.dollop.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.entity.Tags;

public interface TagsRepository extends JpaRepository<Tags, Integer> {

}
