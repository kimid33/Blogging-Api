package com.dollop.app.service;

import com.dollop.app.entity.PostTag;

public interface IPostTagService {
	
	//createPostTag
	/***
	 * 
	 * @param keyword
	 * @return
	 */
	Integer createPostTag(PostTag postTag);
	//void deletePostTag(Integer postTagId);
}
