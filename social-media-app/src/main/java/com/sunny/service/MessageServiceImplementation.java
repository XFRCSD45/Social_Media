package com.sunny.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunny.Repository.ChatRepository;
import com.sunny.Repository.MessageRepository;
import com.sunny.models.Chat;
import com.sunny.models.Message;
import com.sunny.models.User;

@Service
public class MessageServiceImplementation implements MessageService {

	
	@Autowired
	MessageRepository messageRepository;

	@Autowired
	ChatService chatService;
	
	@Autowired
	ChatRepository chatRepository;
	@Override
	public Message createMessage(Message msg, Integer chatId, User user) throws Exception {
		// TODO Auto-generated method stub
		Message newMessage = new Message();
		
		Chat chat = chatService.findChatById(chatId);
		newMessage.setChat(chat);
		newMessage.setUser(user);
		newMessage.setContent(msg.getContent());
		newMessage.setImage(msg.getImage());
		newMessage.setTimeStamp(LocalDateTime.now());
		
		Message savedMessage =messageRepository.save(newMessage);
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception {
		// TODO Auto-generated method stub
		
		Chat chat = chatService.findChatById(chatId);
		
		return messageRepository.findByChatId(chat.getId());
	}
	
	

}
