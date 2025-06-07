package com.blogapp.springblogapp.controller;

import com.blogapp.springblogapp.dto.AddCommentDTO;
import com.blogapp.springblogapp.service.AddCommentService;
import com.blogapp.springblogapp.utils.BlogResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final AddCommentService addCommentService;

    public CommentController(AddCommentService addCommentService) {
        this.addCommentService = addCommentService;
    }

    @PostMapping(path = "/createComment")
    public ResponseEntity <BlogResponse> createComment(@RequestBody AddCommentDTO addCommentDTO){
       BlogResponse createComment =  addCommentService.createComment(addCommentDTO);
       return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

}
