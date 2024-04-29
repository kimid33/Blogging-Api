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

import com.dollop.app.entity.Albums;
import com.dollop.app.service.IAlbumsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/albums")
public class AlbumsController {
	@Autowired
	private IAlbumsService service;
	
	@PostMapping("/create")
	public ResponseEntity<Integer> createAlbums(@Valid @RequestBody Albums albums){
		Integer album = service.createAlbums(albums);
		ResponseEntity<Integer> response = new ResponseEntity<>(album,HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/{albumsId}")
	public ResponseEntity<Albums> getOneAlbum(@PathVariable Integer albumsId){
		Albums album = service.getOneAlbumById(albumsId);
		return new ResponseEntity<Albums>(album,HttpStatus.OK);
	}
	
	@PutMapping("/{albumsId}")
	public ResponseEntity<Albums>updateAlbum(@Valid @PathVariable("albumsId") Integer albumsId, @RequestBody Albums albums){
		Albums album = service.updateAlbums(albums, albumsId);
		return ResponseEntity.ok(album);
	}
	
	@DeleteMapping("/{albumsId}")
	public ResponseEntity<String> deleteAlbum(@PathVariable Integer albumsId){
		service.deleteAlbums(albumsId);
		return new ResponseEntity<String>("album deleted",HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Albums>> getAllAlbums(){
		return ResponseEntity.ok(service.getAllAlbums());
		
	}
}
