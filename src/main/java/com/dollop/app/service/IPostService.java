package com.dollop.app.service;

import java.util.List;

import com.dollop.app.entity.Post;

public interface IPostService {
	//createPost
	/***
	 * 
	 * @param keyword
	 * @return
	 */
	Post createPost(Post post);
	//updatePost
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	Post updatePost(Post post, Integer postId );
	//deletePost
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	void deletePost(Integer postId);
	//getallPosts
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	List<Post> getallPosts();
	//getOnePost
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	Post getOnePost(Integer postId);
	//getaPostList
		/***
		 * 
		 * @param keyword
		 * @return
		 */
	List<Post> getPostList();
}
