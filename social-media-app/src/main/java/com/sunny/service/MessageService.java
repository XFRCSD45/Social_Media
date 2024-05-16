package com.sunny.service;

import java.util.List;

import com.sunny.models.Message;
import com.sunny.models.User;

public interface MessageService {

	public Message createMessage(Message msg, Integer chatId,User user) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
	
}
