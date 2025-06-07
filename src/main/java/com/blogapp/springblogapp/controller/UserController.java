package com.blogapp.springblogapp.controller;

import com.blogapp.springblogapp.dto.UserDTO;
import com.blogapp.springblogapp.entity.User;
import com.blogapp.springblogapp.service.UserService;
import com.blogapp.springblogapp.utils.BlogResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/createUser")
    public ResponseEntity<BlogResponse> createUser(@RequestBody UserDTO userDTO){
      return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.OK);
    }
    @PutMapping(path = "/updateUser/{userId}")
    public ResponseEntity<Optional<User>> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO){
        return  new ResponseEntity<>(userService.UpdateUser(userId, userDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/viewUser/{id}")
    public ResponseEntity<BlogResponse> viewUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.viewUserDetails(id), HttpStatus.OK);
    }
    @PostMapping(path = "/login")
    public ResponseEntity<Optional<BlogResponse>> userLogin(@RequestBody UserDTO userDTO){
       return new ResponseEntity<>(userService.login(userDTO), HttpStatus.OK);
    }
}
