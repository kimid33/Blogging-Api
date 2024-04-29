package com.dollop.app.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="posttag")
public class PostTag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postTagId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="postId")
	private Post post;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="tagId")
	//@JsonIgnoreProperties("postTag")
	private Tags tags;
	private boolean isActive=true;
}
