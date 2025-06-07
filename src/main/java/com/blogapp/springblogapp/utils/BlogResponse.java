package com.blogapp.springblogapp.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogResponse<T> {
    private String messageStatus;
    private String messageCode;
    private T data;

}
