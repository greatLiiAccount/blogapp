package com.blogapp.springblogapp.dto;

import com.blogapp.springblogapp.entity.Post;
import com.blogapp.springblogapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentDTO {
    private String reply;
    private String commentedBy;
    private Long userId;
    private Long postId;
}
