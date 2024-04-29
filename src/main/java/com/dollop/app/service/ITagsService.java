package com.dollop.app.service;

import com.dollop.app.entity.Tags;

public interface ITagsService {
	//createTags
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	Integer createTags(Tags tags);
	//updateTags
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	Tags updateTags(Tags tags,Integer tagsId);
	//deleteTags
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	void deleteTags(Integer tagsId);
}
