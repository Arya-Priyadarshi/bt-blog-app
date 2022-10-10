package com.bt.blog.controller;

import com.bt.blog.dto.RawDTO;
import com.bt.blog.dto.UserBlogsDto;
import com.bt.blog.service.BlogAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bt/admin")
public class BlogAdminController {

    @Autowired
    private BlogAdminService blogAdminService;

    @GetMapping("/usersAndBlogs")
    public ResponseEntity<List<UserBlogsDto> > getAllUsersAndBlogs() {

        List<UserBlogsDto> usersAndBlogs = blogAdminService.getUsersAndBlogs();

        return new ResponseEntity<>(usersAndBlogs, HttpStatus.OK);
    }
}
