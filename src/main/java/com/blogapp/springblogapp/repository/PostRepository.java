package com.blogapp.springblogapp.repository;

import com.blogapp.springblogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
