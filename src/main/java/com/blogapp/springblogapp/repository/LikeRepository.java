package com.blogapp.springblogapp.repository;

import com.blogapp.springblogapp.entity.Like;
import com.blogapp.springblogapp.entity.Post;
import com.blogapp.springblogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
  Boolean existsByUserAndPost (User id,  Post postId);
}
