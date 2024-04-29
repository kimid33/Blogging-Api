package com.dollop.app.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dollop.app.dtos.BadApiRequestException;
import com.dollop.app.entity.Photo;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.PhotoRepository;
import com.dollop.app.service.IPhotoService;

@Service
public class PhotoServiceImpl implements IPhotoService {
	
	@Autowired
	private PhotoRepository prepo;
	@Override
	public Integer createPhoto(Photo photo) {
		Photo p = prepo.save(photo);
		return p.getPhotoId();
	}

	@Override
	public Photo getPhotoById(Integer photoId) {
		Photo photo = prepo.findById(photoId).orElseThrow(()->
		new ResourceNotFoundException("photo not found for given id"+photoId));
		if(photo.isActive())
		{
		   return photo;
		}
		else
		{
			throw new ResourceNotFoundException("photo not found for given id"+photoId);
		}
	}

	@Override
	public Photo updatePhoto(Photo photo, Integer photoId) {
		System.err.println(photoId);
		Photo pho = prepo.findById(photoId).orElseThrow(()-> 
		new ResourceNotFoundException("photo not found for given id"+photoId));
		if(pho.isActive())
		{
		   pho.setPhotoName(photo.getPhotoName());
		   pho.setPhotoThumbNail(photo.getPhotoThumbNail());
		   pho.setPhotoType(photo.getPhotoType());
		   prepo.save(pho);
		}
		else
		{
			throw new ResourceNotFoundException("photo id not activated for updation");
		}
		return pho;
	}

	@Override
	public void deletePhoto(Integer photoId) {
		Photo photo = prepo.findById(photoId).orElseThrow(()->
		new ResourceNotFoundException("photo not found for given id"+photoId));
		if(photo.isActive())
		{
			photo.setActive(false);
			prepo.save(photo);
		}
		else
		{
			throw new ResourceNotFoundException("photo is already deleted for given id"+photoId);
		}
	}

	@Override
	public List<Photo> getAllPhoto() {
		List<Photo> photo = prepo.getAllActivePhotos();
		return photo;
	}

	@Override
	public String uploadFile(MultipartFile file, String path) throws IOException {
		String originalFilename = file.getOriginalFilename();   //ctr+1
		String filename=UUID.randomUUID().toString();
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		String fileNameWithExtension = filename+extension;
		String fullPathWithFileName = path+fileNameWithExtension;
		
		if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg"))
		{
			File folder = new File(path);
			if(!folder.exists())
			{
				folder.mkdirs();
				
			}
			Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
		}
		else
		{
			throw new BadApiRequestException("File With this "+extension+" not allowed");
		}
		return fileNameWithExtension;
	}

	@Override
	public InputStream getResourse(String path, String name) throws FileNotFoundException {
		String fullPath = path+File.separator+name;   // separator :-slash de dega -
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

}
