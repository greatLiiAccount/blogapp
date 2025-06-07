package com.blogapp.springblogapp.service.Impl;

import com.blogapp.springblogapp.dto.LikeDTO;
import com.blogapp.springblogapp.dto.PostDTO;
import com.blogapp.springblogapp.entity.Like;
import com.blogapp.springblogapp.entity.Post;
import com.blogapp.springblogapp.entity.User;
import com.blogapp.springblogapp.repository.LikeRepository;
import com.blogapp.springblogapp.repository.PostRepository;
import com.blogapp.springblogapp.repository.UserRepository;
import com.blogapp.springblogapp.service.PostService;
import com.blogapp.springblogapp.utils.BlogResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }


    @Override
    public BlogResponse createPost(PostDTO postDTO) {
        Optional<User> user = userRepository.findById(postDTO.getUserId());
        if (user.isPresent()){
            Post post = new Post();
            post.setTitle(postDTO.getTitle());
            post.setDescription(postDTO.getDescription());
            post.setUser(user.get());
            post.setCommentCount(0L);
            post.setLikeCount(0L);
           Post saved = postRepository.save(post);

            return BlogResponse.builder()
                    .messageCode("200")
                    .messageStatus("Post Successful")
                    .data(saved)
                    .build();
        }
        return BlogResponse.builder()
                .messageCode("404")
                .messageStatus("User Not found")
                .data(null)
                .build();
    }

    @Override
    public BlogResponse viewPost(Long id) {
       Optional<Post> post =   postRepository.findById(id);
       if (post.isPresent()){
           return BlogResponse.builder()
                   .messageCode("001")
                   .messageStatus("Data Found")
                   .data(post)
                   .build();
       }

        return BlogResponse.builder()
                .messageCode("404 not found")
                .messageStatus("Data not found")
                .build();
    }

    @Override
    @Transactional
    public BlogResponse updateLike(LikeDTO likeDTO) {
        Optional<Post> post = postRepository.findById(likeDTO.getPostId());
        Optional<User> user = userRepository.findById(likeDTO.getUserId());
        if(likeRepository.existsByUserAndPost(user.get(),post.get())){
            Post existingPost = post.get();
            existingPost.setLikeCount(existingPost.getLikeCount() - 1);
           Post saved =  postRepository.save(existingPost);
           return BlogResponse.builder()
                   .messageStatus("Like Already Exists")
                   .messageCode("202")
                   .build();
        }
        Like like = new Like();
        like.setPost(post.get());
        like.setUser(user.get());
        Like saved = likeRepository.save(like);

        Post existingPost = post.get();
        existingPost.setLikeCount(existingPost.getLikeCount() + 1);
        Post savedPost =  postRepository.save(existingPost);
        return BlogResponse.builder()
                .messageCode("002")
                .messageStatus("Like Sent")
                .data(saved)
                .build();
    }


}
