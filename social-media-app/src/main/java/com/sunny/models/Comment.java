package com.sunny.models;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    
	
	@ManyToOne   //one user can create many comments but one comment cant be created by many user 
	private User user;
	
	//the model we are in is denoted first means we are here in comment model so during writing relation comment will be considered as first 
	// for example ManyToOne means many comment one user bcz we are in comment model and OneToMany means one comment many user 
	
	
	public Comment(Integer id, User user, String content, List<User> liked, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.user = user;
		this.content = content;
		this.liked = liked;
		this.createdAt = createdAt;
	}
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<User> getLiked() {
		return liked;
	}

	public void setLiked(List<User> liked) {
		this.liked = liked;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	private String content;
	
	@ManyToMany   //many users can like a comment and one user can like many comments 
	private List<User> liked = new ArrayList<>();
	
    private LocalDateTime createdAt;
}
