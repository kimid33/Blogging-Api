package com.dollop.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.app.entity.ToDoList;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.ToDoListRepository;
import com.dollop.app.service.IToDoListService;

@Service
public class ToDoListServiceImpl implements IToDoListService {
	@Autowired
	private ToDoListRepository todolistRepository;

	@Override
	public Integer createToDoList(ToDoList toDoList) {
		ToDoList todo=todolistRepository.save(toDoList);
		return todo.getToDoListId();
	}

	@Override
	public ToDoList getToDoById(Integer toDoListId) {
		ToDoList todo = todolistRepository.findById(toDoListId)
				.orElseThrow(()->new 
				ResourceNotFoundException("todo list is not found for given id"+toDoListId));
		if(todo.isActive())
		{
		   return todo;
		}
		else
		{
			throw new ResourceNotFoundException("todolist is not found for given id"+toDoListId);
		}
	}

	@Override
	public ToDoList updateToDoList(ToDoList toDoList, Integer toDoListId) {
		ToDoList todoUpdate = todolistRepository.findById(toDoListId)
				.orElseThrow(()-> new 
				ResourceNotFoundException("todo list not found for given id to "+ toDoListId));
		if(todoUpdate.isActive())
		{
		  todoUpdate.setToDoListTitle(toDoList.getToDoListTitle());
		  todolistRepository.save(todoUpdate);
		}
		else
		{
			throw new ResourceNotFoundException("todo List id not activated for updation");
		}
		return todoUpdate;
	}

	@Override
	public void deleteToDoList(Integer toDoListId) {
		ToDoList todo = todolistRepository.findById(toDoListId)
				.orElseThrow(()-> new ResourceNotFoundException("todo list not found for given id "+ toDoListId));
		if(todo.isActive())
		{
			todo.setActive(false);
			todolistRepository.save(todo);
		}
		else
		{
			throw new ResourceNotFoundException("todo list already deactivated");
		}
	}
}
