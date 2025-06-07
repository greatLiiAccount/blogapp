package com.blogapp.springblogapp.controller;

import com.blogapp.springblogapp.dto.LikeDTO;
import com.blogapp.springblogapp.dto.PostDTO;
import com.blogapp.springblogapp.entity.Post;
import com.blogapp.springblogapp.service.PostService;
import com.blogapp.springblogapp.utils.BlogResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@RestController
@RequestMapping(path = "/post")
public class PostController {
    public PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(path = "/createPost")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<BlogResponse> createPost(@RequestBody PostDTO postDTO){
        BlogResponse newPost = postService.createPost(postDTO);
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }
    @GetMapping(path = "/viewPost/{id}")
    public ResponseEntity<BlogResponse> viewPost(@PathVariable Long id){
        BlogResponse viewPost = postService.viewPost(id);
        return new ResponseEntity<>(viewPost, HttpStatus.OK);
    }
    @PostMapping(path = "/updateLike")
    public ResponseEntity<BlogResponse> updateLike(@RequestBody LikeDTO likeDTO){
        BlogResponse newPost = postService.updateLike(likeDTO);
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }
}
