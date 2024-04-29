package com.dollop.app.service;

import java.util.List;

import com.dollop.app.entity.Albums;

public interface IAlbumsService {
	//createAlbums
	/***
	 * 
	 * @param keyword
	 * @return
	 */
	Integer createAlbums(Albums albums);
	//getOneAlbumById
	/***
	 * 
	 * @param keyword
	 * @return
	 */
	Albums getOneAlbumById(Integer albumsId);
	//updateAlbums
	/***
	 * 
	 * @param keyword
	 * @return
	 */
	Albums updateAlbums(Albums albums,Integer albumsId);
	//deleteAlbums
	/***
	 * 
	 * @param keyword
	 * @return
	 */
	void deleteAlbums(Integer albumsId);
	//getAllAlbums
	/***
	 * 
	 * @param keyword
	 * @return
	 */
	List<Albums> getAllAlbums();
	
}
