package com.dollop.app.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.dollop.app.validate.ImageNameValid;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="photo")
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer photoId;
	@NotBlank(message = "photoName is required")
    private String photoName;
    private String photoType;
    @NotBlank(message = "thumbnail is required")
    private String photoThumbNail;
    @ImageNameValid(message = "invalid")
    private String imagePath;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private boolean isActive=true;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="albumsId")
    private Albums albums;
}
