package com.sunny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sunny.models.Comment;
import com.sunny.models.User;
import com.sunny.service.CommentService;
import com.sunny.service.PostService;
import com.sunny.service.UserService;

@RestController
public class CommentController {

	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/api/comments/post/{postId}")
	public Comment createComment (@RequestBody Comment comment, @PathVariable("postId") Integer id, @RequestHeader("Authorization") String token) throws Exception {
		
		User user= userService.findUserByJwt(token);
		
		Comment newComment= commentService.createComment(comment, id, user.getId());
		
		return newComment;
	}
	
	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment( @PathVariable("commentId") Integer id, @RequestHeader("Authorization") String token) throws Exception
	{
		User user= userService.findUserByJwt(token);
		
		Comment likedComment = commentService.likeComment(id, user.getId());
		return likedComment;
	}
}
