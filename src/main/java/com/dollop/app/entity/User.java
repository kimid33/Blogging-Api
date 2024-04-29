package com.dollop.app.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Size(min = 3,max = 30,message = "Invalid Name!!")
	private String userFirstName;
	@Size(min = 3,max = 30,message = "Invalid Name!!")
	private String userLastName;
	@Size(min = 3,max = 30,message = "Invalid Name!!")
	@Column(unique = true)
	private String userName;
	
	@NotBlank(message = "Password is Required !!")
	@Size(max=8)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
	message = "Password must contain at least one digit, one lowercase, one uppercase, one special character, and minimum length of 8 characters")
	private String userPassword;
	@Email(message = "Invalid User Email !!")
	@NotBlank(message = "Email is Required !!")
	@NotEmpty(message = "email cannot be empty")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
	 message = "Invalid email address")
	private String userEmail;
	@NotBlank(message = "Phone is Required !!")
	@Column(unique = true)
	@Pattern(regexp = "^(?:\\+?\\d{2}[\\s-]?)?(?:\\d{10})$",
	message = "Invalid mobile number")
	private String userPhone;
	@NotBlank(message = "Website is required!")
	private String userWebsite;
	@CreationTimestamp
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
	private boolean isActive=true;
	
	@OneToMany
	@JoinColumn(name="userId")
	@JsonIgnoreProperties("user")
	private List<Address> userAddress;
	
	@OneToMany
	@JoinColumn(name="userId")
	@JsonIgnoreProperties("user")
	private List<ToDoList> usertodoList;
	
	@OneToMany
	@JoinColumn(name="userId")
	@JsonIgnoreProperties("user")
	//@JsonIgnore
    private List<Albums> userAlbums;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	@JsonIgnoreProperties("user")
	@JsonIgnore
    private Set<UserRole> userRoles;
	
	@OneToMany
	@JoinColumn(name="userId")
	@JsonIgnoreProperties("user")
	//@JsonIgnore
    private List<Post> userPost;
	
	@OneToMany
	@JoinColumn(name="userId")
	@JsonIgnoreProperties("user")
	//@JsonIgnore
    private List<Comments> userComments;
	
}
