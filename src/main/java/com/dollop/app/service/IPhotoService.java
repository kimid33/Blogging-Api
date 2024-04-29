package com.dollop.app.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dollop.app.entity.Photo;

public interface IPhotoService {
	//createPhoto
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	Integer createPhoto(Photo photo);
	//getPhotoById
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	Photo getPhotoById(Integer photoId);
	//updatePhoto
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	Photo updatePhoto(Photo photo,Integer photoId);
	//deletePhoto
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	void deletePhoto(Integer photoId);
	//getAllPhoto
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	List<Photo> getAllPhoto();
	//uploadFile
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	String uploadFile(MultipartFile file,String path) throws IOException;
	//getResourse
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	public InputStream getResourse(String path, String name) throws FileNotFoundException;
}
