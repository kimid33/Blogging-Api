package com.dollop.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.app.entity.Post;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.PostRepository;
import com.dollop.app.service.IPostService;

@Service
public class PostServiceImpl implements IPostService {
	
	@Autowired
	private PostRepository prepo;
	@Override
	public Post createPost(Post post) {
		return prepo.save(post);
	}
	@Override
	public Post updatePost(Post post, Integer postId) {
		Post p = prepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("resources not found"));
		p.setPostName(post.getPostName());
		p.setPostBody(post.getPostBody());
		 
		return prepo.save(p);
	}

	@Override
	public void deletePost(Integer postId) {
         Post p	= prepo.findById(postId).orElseThrow(
        		 ()-> new ResourceNotFoundException("post not found"));
	     p.setActive(false);
	       prepo.save(p);
	       
	}

	@Override
	public List<Post> getallPosts() {
		return prepo.getAllActivePost();
	
		
	}

	@Override
	public Post getOnePost(Integer postId) {
	              Post post = prepo.findById(postId).orElseThrow(()->new ResourceNotFoundException());
		       if(post.isActive()!=true)
		       {
		    	   throw new ResourceNotFoundException("Post Not Found");
		       }
		       else
		       {
		    	   return post;
		       }
	              
	}

	@Override
	public List<Post> getPostList() {
		
		return prepo.findAll();
	}

}
