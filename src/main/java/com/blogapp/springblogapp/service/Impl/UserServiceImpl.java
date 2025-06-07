package com.blogapp.springblogapp.service.Impl;

import com.blogapp.springblogapp.config.JwtTokenProvider;
import com.blogapp.springblogapp.dto.PostDTO;
import com.blogapp.springblogapp.dto.UserDTO;
import com.blogapp.springblogapp.entity.Post;
import com.blogapp.springblogapp.entity.Role;
import com.blogapp.springblogapp.entity.User;
import com.blogapp.springblogapp.repository.UserRepository;
import com.blogapp.springblogapp.service.PostService;
import com.blogapp.springblogapp.service.UserService;
import com.blogapp.springblogapp.utils.BlogResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public BlogResponse createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return BlogResponse.builder()
                    .messageCode("001")
                    .messageStatus("User Already Exists")
                    .build();
        }

        User newUser = User.builder()
                .name(userDTO.getName())
                .address(userDTO.getAddress())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .phoneNumber(userDTO.getPhoneNumber())
                .role(Role.valueOf("ROLE_ADMIN"))
                .build();
        User saveUser = userRepository.save(newUser);
        return BlogResponse.builder()
                .messageStatus("User Saved Successfully")
                .messageCode("001")
                .build();

    }

    @Override
    public BlogResponse viewUserDetails(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return BlogResponse.builder()
                    .messageCode("200")
                    .messageStatus("User Details Successfully Fetched")
                    .data(user)
                    .build();
        }
        return BlogResponse.builder()
                .messageCode("404")
                .messageStatus("User Not Found")
                .data(null)
                .build();
    }

    @Override
    public Optional<User> UpdateUser(Long userId, UserDTO userDTO) {
        Optional<User> newUser = userRepository.findById(userId);
        User user = newUser.get();
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<BlogResponse> login(UserDTO userDTO) {

        try {
            Authentication authentication = null;
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
            log.info("Generating token for user: {}", userDTO.getEmail());
            return Optional.ofNullable(BlogResponse.builder()
                    .messageCode("001")
                    .messageStatus("Login Success")
                    .data(jwtTokenProvider.generateToken(authentication))
                    .build());
        }catch (AuthenticationException ex){
            return Optional.ofNullable(BlogResponse.builder()
                    .messageCode("404")
                    .messageStatus("Authentication failed")
                    .data(null)
                    .build());
        }

    }
}
