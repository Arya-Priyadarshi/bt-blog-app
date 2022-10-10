package com.bt.blog.dto;

import com.bt.blog.model.blog.Blog;
import com.bt.blog.model.user.User;
import lombok.Data;

import java.util.List;

@Data
public class RawDTO {

    private List<User> users;
    private List<Blog> blogs;
}
