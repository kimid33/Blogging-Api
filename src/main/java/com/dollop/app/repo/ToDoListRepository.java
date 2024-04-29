package com.dollop.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.entity.ToDoList;

public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {

}
