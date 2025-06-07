package com.blogapp.springblogapp.service;

import com.blogapp.springblogapp.dto.UserDTO;
import com.blogapp.springblogapp.entity.User;
import com.blogapp.springblogapp.utils.BlogResponse;

import java.util.Optional;

public interface UserService{
    BlogResponse createUser(UserDTO userDTO);
    BlogResponse viewUserDetails(Long id);
   Optional<User> UpdateUser(Long userId, UserDTO userDTO);
    Optional<BlogResponse> login(UserDTO userDTO);


}
