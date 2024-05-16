package com.sunny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sunny.models.Post;
import com.sunny.models.User;
import com.sunny.response.ApiResponse;
import com.sunny.service.PostService;
import com.sunny.service.UserService;

@RestController
public class PostController {

	@Autowired
	PostService postService;
	
   @Autowired
   UserService userService;
	@PostMapping("/api/posts")
	public ResponseEntity<Post> createNewPost(@RequestBody Post post,@RequestHeader("Authorization") String token) throws Exception
	{
		User reqUser= userService.findUserByJwt(token);
		Post createdPost= postService.createNewPost(post, reqUser.getId());
		
		return new ResponseEntity<Post>(createdPost,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,@RequestHeader("Authorization") String token) throws Exception
	{
		User reqUser= userService.findUserByJwt(token);
		String res = postService.deletePost(postId, reqUser.getId());
		ApiResponse resp = new ApiResponse(res,true);
		return new ResponseEntity<ApiResponse>(resp,HttpStatus.OK);
	}
	
	@GetMapping("/api/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception
	{
		Post post = postService.findPostById(postId);
		
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/posts/user/{userId}")
	public ResponseEntity<List<Post>> findPostsByUserId(@PathVariable Integer userId)
	{
		List<Post> posts=postService.findPostByUserId(userId);
		
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPosts()
	{
		List<Post> posts=postService.findAllPost();
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@PutMapping("/api/posts/save/{postId}")
	public ResponseEntity<Post> savedPostsHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String token) throws Exception
	{
		User reqUser= userService.findUserByJwt(token);
		Post post = postService.savedPost(postId,reqUser.getId());
		return new ResponseEntity<Post>(post,HttpStatus.OK);
		
	}
	
	@PutMapping("/api/posts/like/{postId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String token) throws Exception
	{
		
		User reqUser= userService.findUserByJwt(token);
		Post post = postService.likePost(postId, reqUser.getId());
		return new ResponseEntity<Post>(post,HttpStatus.OK);
		
	}
}
