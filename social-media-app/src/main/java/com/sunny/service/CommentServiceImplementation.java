package com.sunny.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunny.Repository.CommentRepository;
import com.sunny.Repository.PostRepository;
import com.sunny.models.Comment;
import com.sunny.models.Post;
import com.sunny.models.User;

@Service
public class CommentServiceImplementation implements CommentService{

	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		User user = userService.findUserById(userId);
		if(user==null) {
			throw new Exception("User does not exist with given Id");
		}
		
		Post post = postService.findPostById(postId);
		if(post==null) {
			throw new Exception("Post does not exist with given Id");
		}
		
		Comment newComment= new Comment();
		
		newComment.setContent(comment.getContent());
		newComment.setUser(user);
        newComment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(newComment);
        post.getComments().add(newComment);
        postRepository.save(post);
		return newComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception{
		// TODO Auto-generated method stub
		
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isEmpty())
		{
			throw new Exception("Comment not exist with the ID");
		}
			
		return comment.get();
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Comment comment = findCommentById(commentId);
		User user = userService.findUserById(userId);
		if(user==null) {
			throw new Exception("User does not exist with given Id");
		}
		if(comment.getLiked().contains(user))
		{
			comment.getLiked().remove(user);
		}
		else {
			comment.getLiked().add(user);	
		}
		
		commentRepository.save(comment);
		return comment;
	}

}
