package com.bt.blog.service;

import com.bt.blog.dto.UserBlogsDto;
import com.bt.blog.model.blog.Blog;
import com.bt.blog.dto.RawDTO;
import com.bt.blog.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BlogAdminService {

    @Value("${bt.users.url}")
    private String usersUrl;

    @Value("${bt.users.blog.url}")
    private String blogUrl;

    @Autowired
    private RestTemplate template;

    public List<UserBlogsDto> getUsersAndBlogs() {
        RawDTO dto = new RawDTO();
        CompletableFuture.allOf(
              CompletableFuture.supplyAsync(()-> template.exchange(usersUrl, HttpMethod.GET, null, User[].class))
                      .thenAccept(var-> dto.setUsers(Arrays.asList(var.getBody()))),
                              CompletableFuture.supplyAsync(()-> template.exchange(blogUrl, HttpMethod.GET, null, Blog[].class))
                      .thenAccept(var-> dto.setBlogs(Arrays.asList(var.getBody()))))
              .join();

        List<UserBlogsDto> userBlogsDtoList = new ArrayList<>();

        for(User user: dto.getUsers()){
            UserBlogsDto userBlogsDto = new UserBlogsDto();
             List<Blog> blogs = new ArrayList<>();
            for(Blog blog : dto.getBlogs()){
                if(user.getId() == blog.getUserId()){
                    blogs.add(blog);
                }
            }
            userBlogsDto.setUser(user);
            userBlogsDto.setBlogs(blogs);
            userBlogsDtoList.add(userBlogsDto);
        }
        return userBlogsDtoList;
    }
}
