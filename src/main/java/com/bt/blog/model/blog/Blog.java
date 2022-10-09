package com.bt.blog.model.blog;

import lombok.Data;

@Data
public class Blog {
    public int userId;
    public int id;
    public String title;
    public String body;
}
