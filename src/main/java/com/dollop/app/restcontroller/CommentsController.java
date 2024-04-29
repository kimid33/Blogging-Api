package com.dollop.app.restcontroller;

import java.util.List;

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

import com.dollop.app.entity.Comments;
import com.dollop.app.service.ICommentsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentsController {
	@Autowired
	private ICommentsService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> createComments(@Valid @RequestBody Comments comments){
		Integer c = service.createComments(comments);
		String msg = "comment "+c+" is created";
		ResponseEntity<String> response = new ResponseEntity<>(msg,HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/getone/{commentsId}")
	public ResponseEntity<Comments> getById(@PathVariable Integer commentsId){
		Comments c = service.getById(commentsId);
		ResponseEntity<Comments> response = new ResponseEntity<>(c,HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/{commentsId}")
	public ResponseEntity<Comments> updateComments(@Valid @PathVariable("commentsId") Integer commentsId,
			@RequestBody Comments comments){
		Comments commentUpdate = service.updateComments(comments, commentsId);
		return ResponseEntity.ok(commentUpdate);
	}
	
	@DeleteMapping("/{commentsId}")
	public ResponseEntity<String> deleteComments(@PathVariable Integer commentsId){
		service.deleteComments(commentsId);
		return new ResponseEntity<String>("comments deleted",HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Comments>> getAllActiveUsers(){
		return ResponseEntity.ok(service.getAllComments());
	}
}
