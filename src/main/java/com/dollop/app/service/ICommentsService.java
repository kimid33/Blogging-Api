package com.dollop.app.service;

import java.util.List;

import com.dollop.app.entity.Comments;

public interface ICommentsService {
	//createComments
			/***
			 * 
			 * @param keyword
			 * @return
			 */
	Integer createComments(Comments comments);
	//getById
			/***
			 * 
			 * @param keyword
			 * @return
			 */
	Comments getById(Integer commentsId);
	//updateComments
			/***
			 * 
			 * @param keyword
			 * @return
			 */
	Comments updateComments(Comments comments,Integer commentsId);
	//deleteComments
			/***
			 * 
			 * @param keyword
			 * @return
			 */
	void deleteComments(Integer commentsId);
	//getAllComments
			/***
			 * 
			 * @param keyword
			 * @return
			 */
	List<Comments> getAllComments();
}
