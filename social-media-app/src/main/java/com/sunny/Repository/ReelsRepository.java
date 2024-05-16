package com.sunny.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunny.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels, Integer> {
	
	public List<Reels> findByUserId(Integer userId);

}
