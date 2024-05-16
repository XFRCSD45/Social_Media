package com.sunny.service;

import java.util.List;

import com.sunny.models.Chat;
import com.sunny.models.User;

public interface ChatService {

	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
	
	
}
