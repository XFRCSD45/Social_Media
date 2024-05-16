package com.sunny.service;

import java.util.List;

import com.sunny.models.Reels;
import com.sunny.models.User;

public interface ReelsService {

	public Reels createReel(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReels(Integer userId) throws Exception;
}
