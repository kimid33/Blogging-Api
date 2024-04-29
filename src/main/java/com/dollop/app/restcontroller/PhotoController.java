package com.dollop.app.restcontroller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
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
import org.springframework.web.multipart.MultipartFile;

import com.dollop.app.dtos.ImageResponse;
import com.dollop.app.entity.Photo;
import com.dollop.app.service.IPhotoService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/photo")
public class PhotoController {
	
	@Autowired
	private IPhotoService photoService;
	
	@Value("${photo.profile.image.path}")
	private String imageUploadPath;
	
	@PostMapping("/create")
	public ResponseEntity<Integer> createPhotos(@Validated @RequestBody Photo photo){
		Integer p = photoService.createPhoto(photo);
		ResponseEntity<Integer> response = new ResponseEntity<>(p,HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/getone/{photoId}")
	public ResponseEntity<Photo> getOnePhoto(@PathVariable Integer photoId){
		Photo photo = photoService.getPhotoById(photoId);
		ResponseEntity<Photo> response = new ResponseEntity<>(photo,HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/{photoId}")
	public ResponseEntity<Photo>updatePhoto(@Validated @PathVariable("photoId") Integer photoId ,
			@RequestBody Photo photo){
		Photo ph = photoService.updatePhoto(photo, photoId);
		return ResponseEntity.ok(ph);
	}
	
	@PostMapping("/image/{photoId}")
	public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("photos")MultipartFile image,@PathVariable Integer photoId) throws IOException{
		String imageName = photoService.uploadFile(image, imageUploadPath);
		Photo p = photoService.getPhotoById(photoId);
		System.err.println(p);
		p.setImagePath(imageName);
		Photo ph = photoService.updatePhoto(p, photoId);
		ImageResponse imageResponse = ImageResponse.builder().imagePath(imageName)
				.message("image is uploaded !")
				.success(true).status(HttpStatus.CREATED).build();
		return new ResponseEntity<ImageResponse>(imageResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/images/{userId}")
	public void serveUserImage(@PathVariable Integer userId,HttpServletResponse response) throws IOException {
		Photo pho = photoService.getPhotoById(userId);
		InputStream resource = photoService.getResourse(imageUploadPath, pho.getImagePath());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	}
	
	@DeleteMapping("/{photoId}")
	public ResponseEntity<String> deletePhoto(@PathVariable Integer photoId){
		photoService.deletePhoto(photoId);
		return new ResponseEntity<String>("photo deleted",HttpStatus.OK);
	}  
	
	@GetMapping("/allphotos")
	public ResponseEntity<List<Photo>> getAllPhotos(){
		return ResponseEntity.ok(photoService.getAllPhoto());
	}
}
