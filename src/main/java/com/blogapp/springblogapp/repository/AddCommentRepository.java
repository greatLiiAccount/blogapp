package com.blogapp.springblogapp.repository;

import com.blogapp.springblogapp.entity.AddComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddCommentRepository extends JpaRepository<AddComment, Long> {
}
