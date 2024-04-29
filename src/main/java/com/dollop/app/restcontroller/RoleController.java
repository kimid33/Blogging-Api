package com.dollop.app.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dollop.app.service.IRoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private IRoleService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> createRole(){
		 service.createRole();
		return new ResponseEntity<String>("role is created",HttpStatus.CREATED);
	}
}
