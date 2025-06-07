package com.blogapp.springblogapp.dto;

import com.blogapp.springblogapp.entity.Post;
import com.blogapp.springblogapp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long Id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private Role role;

}
