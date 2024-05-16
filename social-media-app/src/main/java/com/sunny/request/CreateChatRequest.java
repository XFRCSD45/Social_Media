package com.sunny.request;

import com.sunny.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateChatRequest {
	
	private Integer userId;
	
}
