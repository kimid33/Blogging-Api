package com.dollop.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.app.entity.Albums;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.AlbumsRepository;
import com.dollop.app.service.IAlbumsService;

@Service
public class AlbumsServiceImpl implements IAlbumsService {
	
	@Autowired
	private AlbumsRepository albumRepository;
	
	@Override
	public Integer createAlbums(Albums albums) {
		Albums alb = albumRepository.save(albums);
		return alb.getAlbumsId();
	}

	@Override
	public Albums getOneAlbumById(Integer albumsId) {
		Albums album = albumRepository.findById(albumsId).orElseThrow(()->
		new ResourceNotFoundException("album not found for given id"+albumsId));
		if(album.isActive())
		{
		   return album;
		}
		else
		{
			throw new ResourceNotFoundException("album not found for given id"+albumsId);
		}
	}

	@Override
	public Albums updateAlbums(Albums albums, Integer albumsId) {
		Albums album = albumRepository.findById(albumsId).orElseThrow(()->
		new ResourceNotFoundException("album not found for given id"+albumsId));
		if(album.isActive())
		{
		  album.setAlbumsName(albums.getAlbumsName());
		  albumRepository.save(album);
		}
		else
		{
			throw new ResourceNotFoundException("album id not activated for updation");
		}
		return album;
	}

	@Override
	public void deleteAlbums(Integer albumsId) {
		Albums album = albumRepository.findById(albumsId).orElseThrow(()->
		new ResourceNotFoundException("album not found for given id"+albumsId));
		if(album.isActive())
		{
			album.setActive(false);
			albumRepository.save(album);
		}
		else 
		{
			throw new ResourceNotFoundException("album not found for given id"+albumsId);
		}
	}

	@Override
	public List<Albums> getAllAlbums() {
		List<Albums> list = albumRepository.getAllActiveAlbums();
		return list;
	}

}
