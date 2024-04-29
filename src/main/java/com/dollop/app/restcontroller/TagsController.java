package com.dollop.app.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dollop.app.entity.Tags;
import com.dollop.app.service.ITagsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tags")
public class TagsController {
	
	@Autowired
	private ITagsService service;
	
	@PostMapping("/create")
	public ResponseEntity<Integer> createTags(@Valid @RequestBody Tags tags){
		Integer tag = service.createTags(tags);
		ResponseEntity<Integer> response = new ResponseEntity<>(tag,HttpStatus.CREATED);
		return response;
	}
	
	@PutMapping("/{tagsId}")
	public ResponseEntity<Tags> updateTags(@Valid @PathVariable("tagsId") Integer tagsId, @RequestBody Tags tags){
		Tags tag = service.updateTags(tags, tagsId);
		return ResponseEntity.ok(tag);
	}
	
	@DeleteMapping("/{tagsId}")
	public ResponseEntity<String> deleteTags(@PathVariable Integer tagsId){
		service.deleteTags(tagsId);
		return new ResponseEntity<String>(" tags deleted",HttpStatus.OK);
	}
}
