package com.dollop.app.entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="albums")
public class Albums {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer albumsId;
	@NotBlank(message = "akbum name can not be blank")
    private String albumsName;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private boolean isActive=true;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userId")
    @JsonIgnoreProperties(value = "user")
    @JsonBackReference
    private User user;
    
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "albumsId")
	@JsonIgnoreProperties(value="albums")
    private List<Photo> photo;
}
