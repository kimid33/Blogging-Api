package com.dollop.app.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dollop.app.entity.ToDoList;
import com.dollop.app.service.IToDoListService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/todolist")
public class ToDoListController {
	
	@Autowired
	private IToDoListService service;
	
	
	@PostMapping("/createtodo")
	public ResponseEntity<Integer> createToDoList(@Valid @RequestBody ToDoList toDoList){
		Integer todo = service.createToDoList(toDoList);
		ResponseEntity<Integer> response = new ResponseEntity<>(todo,HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/{toDoListId}")
	public ResponseEntity<ToDoList> getToDoById(@PathVariable Integer toDoListId){
		ToDoList todo = service.getToDoById(toDoListId);
		ResponseEntity<ToDoList> response = new ResponseEntity<>(todo,HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/{toDoListId}")
	public ResponseEntity<ToDoList> updateToDoList(@Valid @PathVariable("toDoListId") Integer toDoListId,@RequestBody ToDoList toDoList){
		ToDoList todoUpdate = service.updateToDoList(toDoList, toDoListId);
		return ResponseEntity.ok(todoUpdate);
	}
	
	@DeleteMapping("/{toDoListId}")
	public ResponseEntity<String> deleteToDoList(@PathVariable Integer toDoListId){
		service.deleteToDoList(toDoListId);
		return new ResponseEntity<>("deleted",HttpStatus.OK);
	}
}
