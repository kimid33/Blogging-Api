package com.dollop.app.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dollop.app.dtos.PageableResponse;
import com.dollop.app.entity.User;
import com.dollop.app.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> createUser(@Valid @RequestBody User user){
		Integer u=service.createUser(user);
		String msg = "user "+u+" is created";
		ResponseEntity<String> response=new ResponseEntity<>(msg,HttpStatus.CREATED);
		return response;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		service.userRegistration(user);
		return ResponseEntity.ok(" New User registered successfully");
	}
	
	@GetMapping("/getone/{userId}")
	public ResponseEntity<User> getOneUser(@PathVariable Integer userId){
		User user=service.getOneUser(userId);
		ResponseEntity<User> response=new ResponseEntity<>(user,HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@Validated @PathVariable("userId") Integer userId,
			@RequestBody User user ){
		User userupdate = service.updateUser(user, userId); 
		return ResponseEntity.ok(userupdate);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
		service.deleteUser(userId);
		return new ResponseEntity<>("user is deleted",HttpStatus.OK);
	}
	
	@GetMapping("getallactive")
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.ok(service.getAllUser());
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<User>> getUserByName(@PathVariable String keyword){
		return new ResponseEntity<>(service.SearchUser(keyword),HttpStatus.OK); 
	}
	
	@GetMapping("/{userName}/{userPassword}")
	public ResponseEntity<User> getUserByNameAndPassword(@PathVariable String userName,@PathVariable String userPassword){
		User u = service.loginUser(userName, userPassword);
		String msg = "login successfully"; 
		return new ResponseEntity<User>(u,HttpStatus.OK); 
	}
	
	
	@GetMapping("all")
	public ResponseEntity<PageableResponse<User>> getAllUsers(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false)int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
			@RequestParam(value = "sortBy",defaultValue = "userFirstName",required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
			){
		return new ResponseEntity<PageableResponse<User>>(service.getAllUsers(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
	} 
}
