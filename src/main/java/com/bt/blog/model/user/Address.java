package com.bt.blog.model.user;

import lombok.Data;

@Data
public class Address {
    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public Geo geo;
}