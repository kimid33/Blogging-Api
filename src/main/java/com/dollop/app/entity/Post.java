package com.dollop.app.entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="post")
public class Post {
		
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer postId;
	    @NotBlank(message = "postName is required")
	    private String postName;
	    @NotBlank(message = "postBody is required")
	    private String postBody;
	    @CreationTimestamp
	    private Timestamp createdAt;
	    @UpdateTimestamp
	    private Timestamp updatedAt;
	    private Integer createdBy;
	    private Integer updatedBy;
	    private boolean isActive=true;
	    
	    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	    @JoinColumn(name="userId")
	    //@JsonIgnore
	    @JsonIgnoreProperties(value = "user")
	    //@JsonBackReference
	    private User user;
	    
		@OneToMany
		@JoinColumn(name="postId")
		@JsonIgnoreProperties(value={"post","user"})
	    private List<Comments> postComments;
		
		@OneToMany
		@JoinColumn(name="postId")
		@JsonIgnoreProperties(value="post")
	    private List<PostTag> postTag;
}
