package com.sunny.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunny.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	

}
