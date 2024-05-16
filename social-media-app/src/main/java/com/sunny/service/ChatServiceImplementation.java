package com.sunny.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunny.Repository.ChatRepository;
import com.sunny.models.Chat;
import com.sunny.models.User;

@Service
public class ChatServiceImplementation implements ChatService {

	@Autowired
	ChatRepository chatRepository;

	@Override
	public Chat createChat(User reqUser, User user2) {
		// TODO Auto-generated method stub
		
		Chat isExist = chatRepository.findChatByUsersId(user2, reqUser);
		
		if(isExist!=null)
		{
			return isExist;
		}
		Chat newChat = new Chat();
		newChat.getUsers().add(user2);
		newChat.getUsers().add(reqUser);
		newChat.setTimeStamp(LocalDateTime.now());
		return chatRepository.save(newChat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		// TODO Auto-generated method stub
	Optional<Chat> opt= chatRepository.findById(chatId);
	
	if(opt.isEmpty())
	{
		throw new Exception("No chat exists with this ID");
	}
	return opt.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		// TODO Auto-generated method stub
		
		return chatRepository.findByUsersId(userId);
	}
	
}
