package com.dollop.app.entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name="tags")
public class Tags {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tagsId;
	@NotBlank(message = "tagsName is required")
	private  String tagsName;
	@CreationTimestamp
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
	private Integer createdBy;
	private Integer updatedBy;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tagId")
	private List<PostTag> postTag;
	private boolean isActive=true;
}
