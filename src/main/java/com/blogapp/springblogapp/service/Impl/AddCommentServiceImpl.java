package com.blogapp.springblogapp.service.Impl;

import com.blogapp.springblogapp.dto.AddCommentDTO;
import com.blogapp.springblogapp.entity.AddComment;
import com.blogapp.springblogapp.entity.Post;
import com.blogapp.springblogapp.entity.User;
import com.blogapp.springblogapp.repository.AddCommentRepository;
import com.blogapp.springblogapp.repository.PostRepository;
import com.blogapp.springblogapp.repository.UserRepository;
import com.blogapp.springblogapp.service.AddCommentService;
import com.blogapp.springblogapp.utils.BlogResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AddCommentServiceImpl implements AddCommentService {
    private final AddCommentRepository addCommentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public AddCommentServiceImpl(AddCommentRepository addCommentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.addCommentRepository = addCommentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public BlogResponse createComment(AddCommentDTO addCommentDTO) {
        Optional<User> user = userRepository.findById(addCommentDTO.getUserId());
        Optional<Post> post = postRepository.findById(addCommentDTO.getPostId());
        if(user.isPresent() && post.isPresent()){
            AddComment addComment = new AddComment();
            addComment.setReply(addCommentDTO.getReply());
            addComment.setCommentedBy(user.get().getName());
            addComment.setPost(post.get());
            addComment.setUser(user.get());
            AddComment commentSaved = addCommentRepository.save(addComment);
            if(addCommentRepository.existsById(commentSaved.getId())){
                Post existingPost = post.get();
                existingPost.setCommentCount(existingPost.getCommentCount() + 1);
                postRepository.save(existingPost);
            }
            return BlogResponse.builder()
                    .messageCode("Comment Added Successfully")
                    .messageStatus("002")
                    .data(commentSaved)
                    .build();

        }
        return BlogResponse.builder()
                .messageStatus("Comment Not Added")
                .messageCode("404 not Found")
                .data(null)
                .build();
    }
}
