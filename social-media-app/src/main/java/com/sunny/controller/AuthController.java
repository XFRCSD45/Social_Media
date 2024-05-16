package com.sunny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunny.Repository.UserRepository;
import com.sunny.config.jwtProvider;
import com.sunny.models.User;
import com.sunny.request.LoginRequest;
import com.sunny.response.AuthResponse;
import com.sunny.service.CustomerUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	CustomerUserDetailsService customerUserDetails;
	//auth/signup
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception
	{
		User isExist =userRepository.findByEmail(user.getEmail());
		  
		if(isExist!=null)
		{
			throw new Exception("Email id is already registered . Please Sign In to your account");
		}
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstname(user.getFirstname());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepository.save(newUser);
		
		Authentication authentication =new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		String token =  jwtProvider.generateToken(authentication);
		
		AuthResponse res= new AuthResponse(token,"Registration Successfull");
		
		return res;
	}
	
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest  )
	{
		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		
String token =  jwtProvider.generateToken(authentication);
		
		AuthResponse res= new AuthResponse(token,"LogIn Successfull");
		
		return res;
	}
	
	private Authentication authenticate(String email, String password)
	{
		UserDetails userDetails = customerUserDetails.loadUserByUsername(email);
		
		if(userDetails==null)
		{
			throw new BadCredentialsException("Invalid Username");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword()))
		{
			throw new BadCredentialsException("Invalid Username or Password");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
	}
}
