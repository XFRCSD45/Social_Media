package com.sunny.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunny.Repository.ReelsRepository;
import com.sunny.models.Reels;
import com.sunny.models.User;

@Service
public class ReelsServiceImplementation implements ReelsService{

	@Autowired
	ReelsRepository reelsRepository;
	
	@Autowired
	UserService userService;
	
	@Override
	public Reels createReel(Reels reel, User user) {
		// TODO Auto-generated method stub
		
		Reels newReels =  new Reels(null, reel.getTitle(), reel.getVideo(), user);
		
		reelsRepository.save(newReels);
		
		return newReels;
	}

	@Override
	public List<Reels> findAllReels() {
		// TODO Auto-generated method stub
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUsersReels(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		User user = userService.findUserById(userId);
		
		
		return reelsRepository.findByUserId(user.getId());
	}

}
