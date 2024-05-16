package com.sunny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sunny.models.Chat;
import com.sunny.models.User;
import com.sunny.request.CreateChatRequest;
import com.sunny.service.ChatService;
import com.sunny.service.UserService;

@RestController
public class ChatController {

	@Autowired
	ChatService chatService;
	
	@Autowired 
	UserService userService;
	
	@PostMapping("/api/chats")
	public Chat createChat(@RequestBody CreateChatRequest req, @RequestHeader("Authorization") String token) throws Exception
	{
		User user = userService.findUserByJwt(token);
		User user2= userService.findUserById(req.getUserId());
		Chat chat = chatService.createChat(user, user2);
		
		return chat;
	}
	
	@GetMapping("/api/chats")
	public List<Chat> getUsersChat( @RequestHeader("Authorization") String token) {
		
		User user = userService.findUserByJwt(token);
		
		List<Chat> chat = chatService.findUsersChat(user.getId());
		
		return  chat;
	}
}
