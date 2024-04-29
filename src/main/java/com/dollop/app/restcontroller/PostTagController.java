package com.dollop.app.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dollop.app.entity.PostTag;
import com.dollop.app.service.IPostTagService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posttag")
public class PostTagController {
	@Autowired
	private IPostTagService service;
	
	@PostMapping("/create")
	public ResponseEntity<Integer> createPosttag(@Valid @RequestBody PostTag postTag){
		Integer postT = service.createPostTag(postTag);
		ResponseEntity<Integer> response = new ResponseEntity<Integer>(postT,HttpStatus.CREATED);
		return response;
	}
	
//	@DeleteMapping("/{postTagId}")
//	public ResponseEntity<String> deletePost(@PathVariable Integer postTagId){
//		service.deletePostTag(postTagId);
//		return new ResponseEntity<String>("postTag deleted",HttpStatus.OK);
//	}
}
