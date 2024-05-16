package com.sunny.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunny.Repository.UserRepository;
import com.sunny.config.jwtProvider;
import com.sunny.models.User;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstname(user.getFirstname());
		newUser.setLastName(user.getLastName());
		newUser.setId(user.getId());
		newUser.setPassword(user.getPassword());
		
		User savedUser = userRepository.save(newUser);
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		 Optional<User> user= userRepository.findById(userId);
	        if(user.isPresent())
			return user.get();
	        throw new Exception("user does not exist with id "+userId);
	}

	@Override
	public User findUserByEmail(String email){
		User user= userRepository.findByEmail(email);
	      return user;
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception {
		// TODO Auto-generated method stub
		User user1=findUserById(userId1);
		User user2=findUserById(userId2);
		
		user2.getFollowers().add(user1.getId());
		user1.getFollowings().add(user2.getId());
		userRepository.save(user1);
		userRepository.save(user2);
		return user1;
	}

	@Override
	public User updateUser(User user,Integer userId) throws Exception{
		Optional<User> user1= userRepository.findById(userId);
		if(user1.isEmpty())
		{
			throw new Exception("user does not exist with id "+userId);	
		}
		User oldUser = user1.get();
		if(user.getFirstname() != null)
			oldUser.setFirstname(user.getFirstname());
		if(user.getLastName() != null)
			oldUser.setLastName(user.getLastName());
		if(user.getEmail() != null)
			oldUser.setEmail(user.getEmail());
		if(user.getGender() != null)
			oldUser.setGender(user.getGender());
		User updatedUser = userRepository.save(oldUser);
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		// TODO Auto-generated method stub
		return userRepository.searchUser(query) ;
	}

	@Override
	public User findUserByJwt(String jwt) {
		// TODO Auto-generated method stub
		
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		
		User user = userRepository.findByEmail(email);
		return user;
	}

}
