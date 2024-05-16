package com.sunny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sunny.models.Message;
import com.sunny.models.User;
import com.sunny.service.MessageService;
import com.sunny.service.UserService;


@RestController
public class MessageController {
  
	@Autowired
	MessageService messageService;
	
	
	@Autowired
	UserService userService;
	
	@PostMapping("/api/messages/chat/{chatId}")
	public Message createMessage(@RequestBody Message msg, @RequestHeader("Authorization") String token, @PathVariable Integer chatId) throws Exception
	{
		User user = userService.findUserByJwt(token);
		
		
		return messageService.createMessage(msg, chatId, user);
		
	}
	
	@GetMapping("/api/messages/chat/{chatId}")
	public List<Message> createMessage( @PathVariable Integer chatId) throws Exception
	{
		
		
		return messageService.findChatsMessages(chatId);
		
	}
	
	
}
