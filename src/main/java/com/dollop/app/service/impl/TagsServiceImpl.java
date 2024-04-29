package com.dollop.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.app.entity.Tags;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.TagsRepository;
import com.dollop.app.service.ITagsService;

@Service
public class TagsServiceImpl implements ITagsService {
	
	@Autowired
	private TagsRepository tagsRepository;
	@Override
	public Integer createTags(Tags tags) {
		Tags tag = tagsRepository.save(tags);
		return tag.getTagsId();
	}

	@Override
	public Tags updateTags(Tags tags, Integer tagsId) {
		Tags tag = tagsRepository.findById(tagsId).orElseThrow(()-> 
		new ResourceNotFoundException("tag is not found for given id"+tagsId));
		if(tag.isActive())
		{
		   tag.setTagsName(tags.getTagsName());
		   tagsRepository.save(tag);
		}
		else
		{
			throw new ResourceNotFoundException("tag id "+tagsId+" is not activated for updation");
		}
		return tag;
	}

	@Override
	public void deleteTags(Integer tagsId) {
		Tags tag = tagsRepository.findById(tagsId).orElseThrow(()->
		new ResourceNotFoundException("tag is not found for given id"+tagsId));
		if(tag.isActive())
		{
			tag.setActive(false);
			tagsRepository.save(tag);
			
		}
		else
		{
			throw new ResourceNotFoundException("tag is already deleted for given id"+tagsId);
		}
	}

}
