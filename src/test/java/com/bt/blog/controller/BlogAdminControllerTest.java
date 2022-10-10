package com.bt.blog.controller;

import com.bt.blog.dto.UserBlogsDto;
import com.bt.blog.model.blog.Blog;
import com.bt.blog.model.user.Address;
import com.bt.blog.model.user.Company;
import com.bt.blog.model.user.Geo;
import com.bt.blog.model.user.User;
import com.bt.blog.service.BlogAdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest
public class BlogAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogAdminService blogAdminService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetAllUsersAndBlogs() throws Exception {
        User user = new User();
        user.setId(15);
        user.setName("Arya Priyadarshi");
        user.setUsername("Arya");
        user.setEmail("arya.priyadarshi@yamil.com");
        user.setPhone("61-770-736-8031");
        user.setWebsite("www.arya.org");

        Geo geo = new Geo();
        geo.setLat("-37.3159");
        geo.setLng("81.1496");

        Address add = new Address();
        add.setStreet("George S");
        add.setSuite("Apt. 556");
        add.setCity("Sydney");
        add.setZipcode("4003");
        add.setGeo(geo);

        user.setAddress(add);

        Company com = new Company();
        com.setName("Arya Domain");
        com.setCatchPhrase("Multi-layered client-server neural-net");
        com.setCatchPhrase("harness real-time e-markets");
        user.setCompany(com);


        Blog blog = new Blog();
        blog.setUserId(15);
        blog.setId(5);
        blog.setTitle("Arya blog");
        blog.setBody("Arya blog in detail");

        UserBlogsDto userBlogsDto = new UserBlogsDto();
        userBlogsDto.setUser(user);
        userBlogsDto.setBlogs(List.of(blog));


        Mockito.when(blogAdminService.getUsersAndBlogs()).thenReturn(List.of(userBlogsDto));
        mockMvc.perform(get("/bt/admin/usersAndBlogs")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)));
    }
}
