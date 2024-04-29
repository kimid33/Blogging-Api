package com.dollop.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.app.entity.PostTag;
import com.dollop.app.repo.PostTagRepository;
import com.dollop.app.service.IPostTagService;

@Service
public class PostTagServiceImpl implements IPostTagService {
	
	@Autowired
	private PostTagRepository postTagRepository;
	
	@Override
	public Integer createPostTag(PostTag postTag) {
		
		return null;
	}

//	@Override
//	public void deletePostTag(Integer postTagId) {
//		PostTag post = postTagRepository.findById(postTagId).orElseThrow(()->
//		new ResourceNotFoundException("posttag is not found for given id"+postTagId));
//		if(post.isActive())
//		{
//			post.setActive(false);
//			postTagRepository.save(post);
//		}
//		else
//		{
//			throw new ResourceNotFoundException("posttag is already deleted for given id"+postTagId);
//		}
//	}

}
