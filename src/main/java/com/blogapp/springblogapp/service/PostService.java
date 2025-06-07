package com.blogapp.springblogapp.service;

import com.blogapp.springblogapp.dto.LikeDTO;
import com.blogapp.springblogapp.dto.PostDTO;
import com.blogapp.springblogapp.entity.Post;
import com.blogapp.springblogapp.utils.BlogResponse;

import java.util.List;
import java.util.Optional;

public interface PostService {
    BlogResponse createPost(PostDTO postDTO);
   BlogResponse viewPost(Long id);
   BlogResponse updateLike(LikeDTO likeDTO);

}
