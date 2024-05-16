package com.sunny.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sunny.models.Reels;
import com.sunny.models.User;
import com.sunny.service.ReelsService;
import com.sunny.service.UserService;

@RestController
public class ReelsController {

	@Autowired
	ReelsService reelsService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createReel(@RequestBody Reels reel,@RequestHeader("Authorization") String token)
	{
		User user = userService.findUserByJwt(token);
		
		Reels createdReel= reelsService.createReel(reel, user);
		return createdReel;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels()
	{
		return reelsService.findAllReels();
	}
	
	@GetMapping("/api/reels/users/{userId}")
	public List<Reels> findUsersReels(@PathVariable Integer userId) throws Exception
	{
		
		
		return reelsService.findUsersReels(userId);
	}
}
