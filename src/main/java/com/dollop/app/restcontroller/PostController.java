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

import com.dollop.app.entity.Post;
import com.dollop.app.service.IPostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	private IPostService service;
	
	@PostMapping("/save")
	private ResponseEntity<Post> createPost(@Valid @RequestBody Post post ){
        return new ResponseEntity<>( service.createPost(post),HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{postId}")
	private ResponseEntity<Post> updatePost(@RequestBody Post post,@PathVariable ("postId") Integer postId ){
       Post	p =	service.updatePost(post, postId);
		
		return new ResponseEntity<>(p,HttpStatus.OK);
		
	}
	
	@GetMapping("/{postId}")
	private ResponseEntity<Post> getPost(@PathVariable Integer postId)
	{
		return ResponseEntity.ok(service.getOnePost(postId));

	}
	
	@DeleteMapping("/{postId}")
	private ResponseEntity<String> deletePost(@PathVariable Integer postId){
		service.deletePost(postId);
		
		return new ResponseEntity<>("post is deleted",HttpStatus.OK);
	
	}
	
	@GetMapping("/getall")
	private ResponseEntity<List<Post>> getAll(){
		
		return new ResponseEntity<>(service.getallPosts(),HttpStatus.OK);
		
	}
}
