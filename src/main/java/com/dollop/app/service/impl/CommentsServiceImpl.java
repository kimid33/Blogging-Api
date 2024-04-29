package com.dollop.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.app.entity.Comments;
import com.dollop.app.entity.User;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.CommentsRepository;
import com.dollop.app.repo.UserRepository;
import com.dollop.app.service.ICommentsService;

@Service
public class CommentsServiceImpl implements ICommentsService {
	@Autowired
	private CommentsRepository crepo;
	
	@Autowired
	private UserRepository urepo;
	
	@Override
	public Integer createComments(Comments comments) {
//		System.out.println(comments.getUser().getUserId());
		System.out.println(comments.getPost().getPostId());
		Comments c = crepo.save(comments);
		return c.getCommentsId();
	}

	@Override
	public Comments getById(Integer commentsId) {
		Comments c=crepo.findById(commentsId)
				.orElseThrow(()->
		new ResourceNotFoundException("comment not found for given id"+commentsId));
		Integer i = c.getCreatedBy();
		User u = urepo.findById(i).get();
		
		if(u.isActive())
		{
			return c;
		}
		else
		{
			throw new RuntimeException("comments not found for given id"+commentsId);
		}
	}

	@Override
	public Comments updateComments(Comments comments, Integer commentsId) {
		Comments comment = crepo.findById(commentsId).orElseThrow(()-> 
		new ResourceNotFoundException("comment not found for given id"+commentsId)); 
		if(comment.isActive())
		{
		   comment.setCommentsBody(comments.getCommentsBody());
		   crepo.save(comment);
		}
		else
		{
			throw new ResourceNotFoundException("comment id not activated for updation");
		}
		return comment;
	}

	@Override
	public void deleteComments(Integer commentsId) {
		Comments comment = crepo.findById(commentsId).orElseThrow(()-> 
		new ResourceNotFoundException("comment not found for given id"+commentsId));
		if(comment.isActive()) {
			comment.setActive(false);
			crepo.save(comment);
		}
		else
		{
			throw new RuntimeException("comment is already deleted for given id "+commentsId);
		}
		
	}

	@Override
	public List<Comments> getAllComments() {
		List<Comments> list = crepo.getAllActiveByTrue();
		return list;
	}

}
