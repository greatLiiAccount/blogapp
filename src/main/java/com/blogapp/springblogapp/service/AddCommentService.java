package com.blogapp.springblogapp.service;

import com.blogapp.springblogapp.dto.AddCommentDTO;
import com.blogapp.springblogapp.utils.BlogResponse;

public interface AddCommentService {
    BlogResponse createComment(AddCommentDTO addCommentDTO);
}
